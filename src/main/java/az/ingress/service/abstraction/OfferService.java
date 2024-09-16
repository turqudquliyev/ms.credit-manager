package az.ingress.service.abstraction;

import az.ingress.model.response.OfferResponse;

import java.util.List;

public interface OfferService {

    List<OfferResponse> getOffersByCreditId(Long creditId);
}