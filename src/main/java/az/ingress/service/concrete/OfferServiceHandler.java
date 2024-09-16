package az.ingress.service.concrete;

import az.ingress.annotation.Log;
import az.ingress.dao.repository.OfferRepository;
import az.ingress.model.response.OfferResponse;
import az.ingress.service.abstraction.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.mapper.OfferMapper.OFFER_MAPPER;

@Log
@Service
@RequiredArgsConstructor
public class OfferServiceHandler implements OfferService {
    private final OfferRepository offerRepository;

    public List<OfferResponse> getOffersByCreditId(Long creditId) {
        var offers = offerRepository.findAllByCreditId(creditId);
        return OFFER_MAPPER.buildOfferResponseList(offers);
    }
}