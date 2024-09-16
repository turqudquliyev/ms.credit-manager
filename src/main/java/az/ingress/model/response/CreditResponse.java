package az.ingress.model.response;

import az.ingress.model.dto.StatusHistoryDto;
import az.ingress.model.enums.CreditStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreditResponse {
    private Long id;
    private CreditStatus status;
    private Integer term;
    private BigDecimal amount;
    private BigDecimal interest;
    private BigDecimal monthlyPayment;
    private Long conveyorId;
    private LocalDateTime checkDate;
    private List<StatusHistoryDto> statusHistories;
}