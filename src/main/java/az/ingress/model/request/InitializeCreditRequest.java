package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InitializeCreditRequest {

    @NotNull(message = "validation.not.null-customerId")
    private Long customerId;

    @NotNull(message = "validation.not.null-term")
    @Min(value = 1, message = "validation.min-term")
    private Integer term;

    @NotNull(message = "validation.not.null-amount")
    @DecimalMin(value = "0.01", message = "validation.min-amount")
    private BigDecimal amount;

    @NotNull(message = "validation.not.null-interest")
    @DecimalMin(value = "0.00", message = "validation.min-interest")
    private BigDecimal interest;

    @NotNull(message = "validation.not.null-monthlyPayment")
    @DecimalMin(value = "0.00", message = "validation.min-monthlyPayment")
    private BigDecimal monthlyPayment;
}