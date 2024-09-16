package az.ingress.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OfferResponse {
    private Long id;
    private Boolean accepted;
    private Integer term;
    private BigDecimal amount;
    private BigDecimal interest;
    private BigDecimal monthlyPayment;
    private BigDecimal minimumAmount;
}