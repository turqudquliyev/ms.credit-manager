package az.ingress.service.concrete;

import az.ingress.annotation.Log;
import az.ingress.client.CreditConveyorClient;
import az.ingress.dao.entity.CreditEntity;
import az.ingress.dao.repository.CreditRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.model.client.dto.ProductDto;
import az.ingress.model.client.request.ConveyorUpdateOfferRequest;
import az.ingress.model.client.response.ConveyorOfferResponse;
import az.ingress.model.request.InitializeCreditRequest;
import az.ingress.model.response.CreditResponse;
import az.ingress.service.abstraction.CreditService;
import az.ingress.service.abstraction.CreditStatusService;
import az.ingress.service.abstraction.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static az.ingress.exception.ErrorMessage.CREDIT_NOT_FOUND;
import static az.ingress.mapper.CreditConveyorMapper.CREDIT_CONVEYOR_MAPPER;
import static az.ingress.mapper.CreditMapper.CREDIT_MAPPER;
import static az.ingress.mapper.OfferMapper.OFFER_MAPPER;
import static az.ingress.model.enums.CreditStatus.ACCEPTED;
import static az.ingress.model.enums.CreditStatus.DRAFT;
import static az.ingress.model.enums.CreditStatus.EXPIRED;
import static az.ingress.model.enums.CreditStatus.REJECTED;
import static java.time.LocalDateTime.now;

@Log
@Service
@RequiredArgsConstructor
public class CreditServiceHandler implements CreditService {
    private final CreditRepository creditRepository;
    private final CustomerService customerService;
    private final CreditStatusService creditStatusService;
    private final CreditConveyorClient conveyorClient;

    public void initializeCredit(InitializeCreditRequest creditRequest) {
        var customerEntity = customerService.fetchCustomerIfExist(creditRequest.getCustomerId());
        var conveyorOfferResponse = generateOffers(creditRequest);

        var creditEntity = CREDIT_MAPPER.toCreditEntity(conveyorOfferResponse);

        CREDIT_MAPPER.setCustomerRelation(creditEntity, customerEntity);
        setOfferRelation(creditEntity, conveyorOfferResponse.getProducts());

        creditStatusService.updateCreditStatus(creditEntity, DRAFT);
        creditRepository.save(creditEntity);
    }

    public CreditResponse getCreditById(Long id) {
        var creditEntity = fetchCreditWithStatusHistoriesIfExist(id);
        return CREDIT_MAPPER.toCreditResponse(creditEntity);
    }

    public void updateExpiredCreditStatus() {
        var credits = creditRepository.findAllByCheckDateBeforeAndStatus(now(), DRAFT);
        credits.forEach(credit -> creditStatusService.updateCreditStatus(credit, EXPIRED));
        creditRepository.saveAll(credits);
    }

    @Transactional
    public void acceptCredit(Long id, Long offerId) {
        var credit = fetchCreditWithOffersIfExist(id);

        CREDIT_MAPPER.setAcceptedOfferDetails(credit, offerId);
        creditStatusService.updateCreditStatus(credit, ACCEPTED);

        processUpdateOfferStatus(credit.getConveyorId(), offerId);
    }

    @Transactional
    public void rejectCredit(Long id) {
        var credit = fetchCreditWithOffersIfExist(id);
        creditStatusService.updateCreditStatus(credit, REJECTED);
    }

    private ConveyorOfferResponse generateOffers(InitializeCreditRequest creditRequest) {
        var conveyorOfferRequest = CREDIT_CONVEYOR_MAPPER.toCreateOfferRequest(creditRequest);
        return conveyorClient.generateOffers(conveyorOfferRequest);
    }

    private void setOfferRelation(CreditEntity creditEntity, List<ProductDto> products) {
        var offers = OFFER_MAPPER.buildOfferEntityList(products, creditEntity);
        CREDIT_MAPPER.setOfferRelation(creditEntity, offers);
    }

    private void processUpdateOfferStatus(Long conveyorId, Long offerId) {
        var offerRequest = ConveyorUpdateOfferRequest.of(offerId, ACCEPTED);
        conveyorClient.updateOfferStatus(conveyorId, offerRequest);
    }

    private CreditEntity fetchCreditWithStatusHistoriesIfExist(Long id) {
        return creditRepository.findWithStatusHistoriesById(id)
                               .orElseThrow(() -> new NotFoundException(CREDIT_NOT_FOUND.getCode()));
    }

    private CreditEntity fetchCreditWithOffersIfExist(Long id) {
        return creditRepository.findWithOffersByIdAndStatus(id, DRAFT)
                               .orElseThrow(() -> new NotFoundException(CREDIT_NOT_FOUND.getCode()));
    }
}