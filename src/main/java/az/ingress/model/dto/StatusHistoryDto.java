package az.ingress.model.dto;

import az.ingress.model.enums.CreditStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StatusHistoryDto {
    private Long id;
    private CreditStatus status;
}