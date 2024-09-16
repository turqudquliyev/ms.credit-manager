package az.ingress.model.constants;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class OfferConstants {
    public static final BigDecimal MINIMUM_AMOUNT = BigDecimal.valueOf(300);
}