package az.ingress.model.client.request;

import az.ingress.model.enums.CreditStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Data
public class ConveyorUpdateOfferRequest {
    private Long productId;
    private CreditStatus status;
}