package az.ingress.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    UNEXPECTED_ERROR("unexpected.error"),
    VALIDATION_ERROR("validation.error"),
    CLIENT_ERROR("client.error"),
    CUSTOMER_NOT_FOUND("not.found-customer"),
    CUSTOMER_ALREADY_EXISTS("already.exists-customer"),
    OFFER_NOT_FOUND("not.found-offer"),
    CREDIT_NOT_FOUND("not.found-credit");

    private final String code;
}