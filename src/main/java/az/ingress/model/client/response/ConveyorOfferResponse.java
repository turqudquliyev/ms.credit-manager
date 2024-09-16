package az.ingress.model.client.response;

import az.ingress.model.client.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static az.ingress.model.constants.DateTimeConstants.DATE_TIME_PATTERN;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConveyorOfferResponse {
    private Long conveyorId;
    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime checkDate;
    private List<ProductDto> products;
}