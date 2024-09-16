package az.ingress.model.client.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ConveyorCreateOfferRequest {
    private Integer term;
    private BigDecimal amount;
    private BigDecimal interest;
    private BigDecimal monthlyPayment;
}