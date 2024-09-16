package az.ingress.mapper;

import az.ingress.dao.entity.CreditEntity;
import az.ingress.dao.entity.CustomerEntity;
import az.ingress.dao.entity.OfferEntity;
import az.ingress.dao.entity.StatusHistoryEntity;
import az.ingress.model.client.response.ConveyorOfferResponse;
import az.ingress.model.enums.CreditStatus;
import az.ingress.model.response.CreditResponse;

import java.util.List;

import static az.ingress.mapper.OfferMapper.OFFER_MAPPER;
import static az.ingress.mapper.StatusHistoryMapper.STATUS_HISTORY_MAPPER;
import static az.ingress.model.enums.CreditStatus.DRAFT;

public enum CreditMapper {
    CREDIT_MAPPER;

    public CreditEntity toCreditEntity(ConveyorOfferResponse offerResponse) {
        return CreditEntity.builder()
                           .status(DRAFT)
                           .conveyorId(offerResponse.getConveyorId())
                           .checkDate(offerResponse.getCheckDate())
                           .build();
    }

    public CreditResponse toCreditResponse(CreditEntity creditEntity) {
        return CreditResponse.builder()
                             .id(creditEntity.getId())
                             .status(creditEntity.getStatus())
                             .term(creditEntity.getTerm())
                             .amount(creditEntity.getAmount())
                             .interest(creditEntity.getInterest())
                             .monthlyPayment(creditEntity.getMonthlyPayment())
                             .conveyorId(creditEntity.getConveyorId())
                             .checkDate(creditEntity.getCheckDate())
                             .statusHistories(STATUS_HISTORY_MAPPER.buildStatusHistoryDtoList(creditEntity.getStatusHistories()))
                             .build();
    }

    public void setCustomerRelation(CreditEntity creditEntity, CustomerEntity customerEntity) {
        customerEntity.setCredits(List.of(creditEntity));
        creditEntity.setCustomer(customerEntity);
    }

    public void setOfferRelation(CreditEntity creditEntity, List<OfferEntity> offers) {
        creditEntity.setOffers(offers);
    }

    public void setStatusHistoryRelation(CreditEntity creditEntity, StatusHistoryEntity statusHistory) {
        creditEntity.setStatusHistories(List.of(statusHistory));
    }

    public void setStatus(CreditEntity creditEntity, CreditStatus status) {
        creditEntity.setStatus(status);
    }

    public void setAcceptedOfferDetails(CreditEntity credit, Long offerId) {
        var acceptedOffer = OFFER_MAPPER.buildAcceptedOfferIfPresent(credit.getOffers(), offerId);
        setOfferDetails(credit, acceptedOffer);
    }

    private void setOfferDetails(CreditEntity credit, OfferEntity offer) {
        credit.setTerm(offer.getTerm());
        credit.setAmount(offer.getAmount());
        credit.setInterest(offer.getInterest());
        credit.setMonthlyPayment(offer.getMonthlyPayment());
    }
}