package az.ingress.util;

import java.math.BigDecimal;

import static az.ingress.model.constants.OfferConstants.MINIMUM_AMOUNT;

public enum OfferUtil {
    OFFER_UTIL;

    public BigDecimal calculateMinimumAmount(BigDecimal amount) {
        return amount.max(MINIMUM_AMOUNT);
    }
}