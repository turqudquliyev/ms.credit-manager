package az.ingress.mapper;

import az.ingress.dao.entity.CreditEntity;
import az.ingress.dao.entity.OfferEntity;
import az.ingress.exception.NotFoundException;
import az.ingress.model.client.dto.ProductDto;
import az.ingress.model.response.OfferResponse;

import java.util.List;

import static az.ingress.exception.ErrorMessage.OFFER_NOT_FOUND;
import static az.ingress.util.OfferUtil.OFFER_UTIL;

public enum OfferMapper {
    OFFER_MAPPER;

    public List<OfferEntity> buildOfferEntityList(List<ProductDto> products, CreditEntity creditEntity) {
        return products.stream()
                       .map(product -> toOfferEntity(product, creditEntity))
                       .toList();
    }

    public List<OfferResponse> buildOfferResponseList(List<OfferEntity> offers) {
        return offers.stream()
                     .map(OFFER_MAPPER::toOfferResponse)
                     .toList();
    }

    public OfferEntity buildAcceptedOfferIfPresent(List<OfferEntity> offers, Long offerId) {
        var offer = offers.stream()
                          .filter(offerEntity -> offerEntity.getId().equals(offerId))
                          .findFirst()
                          .orElseThrow(() -> new NotFoundException(OFFER_NOT_FOUND.getCode()));
        offer.setAccepted(true);
        return offer;
    }

    private OfferEntity toOfferEntity(ProductDto productDto, CreditEntity creditEntity) {
        return OfferEntity.builder()
                          .conveyorProductId(productDto.getProductId())
                          .term(productDto.getTerm())
                          .amount(productDto.getAmount())
                          .interest(productDto.getInterest())
                          .monthlyPayment(productDto.getMonthlyPayment())
                          .credit(creditEntity)
                          .build();
    }

    private OfferResponse toOfferResponse(OfferEntity offerEntity) {
        return OfferResponse.builder()
                            .id(offerEntity.getId())
                            .accepted(offerEntity.isAccepted())
                            .term(offerEntity.getTerm())
                            .amount(offerEntity.getAmount())
                            .interest(offerEntity.getInterest())
                            .monthlyPayment(offerEntity.getMonthlyPayment())
                            .minimumAmount(OFFER_UTIL.calculateMinimumAmount(offerEntity.getAmount()))
                            .build();
    }
}