package az.ingress.model.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    private Long productId;
    private Integer term;
    private BigDecimal amount;
    private BigDecimal interest;
    private BigDecimal monthlyPayment;
}