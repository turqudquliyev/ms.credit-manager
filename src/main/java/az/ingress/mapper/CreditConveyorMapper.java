package az.ingress.mapper;

import az.ingress.model.client.request.ConveyorCreateOfferRequest;
import az.ingress.model.request.InitializeCreditRequest;

public enum CreditConveyorMapper {
    CREDIT_CONVEYOR_MAPPER;

    public ConveyorCreateOfferRequest toCreateOfferRequest(InitializeCreditRequest creditRequest) {
        return ConveyorCreateOfferRequest.builder()
                                         .term(creditRequest.getTerm())
                                         .amount(creditRequest.getAmount())
                                         .interest(creditRequest.getInterest())
                                         .monthlyPayment(creditRequest.getMonthlyPayment())
                                         .build();
    }
}