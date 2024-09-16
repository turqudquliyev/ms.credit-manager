package az.ingress.client;

import az.ingress.client.decoder.CustomErrorDecoder;
import az.ingress.model.client.request.ConveyorCreateOfferRequest;
import az.ingress.model.client.request.ConveyorUpdateOfferRequest;
import az.ingress.model.client.response.ConveyorOfferResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "credit-conveyor", url = "${client.urls.credit-conveyor}", configuration = CustomErrorDecoder.class)
public interface CreditConveyorClient {

    @PostMapping("v1/credit-conveyor/offers")
    ConveyorOfferResponse generateOffers(@RequestBody ConveyorCreateOfferRequest offerRequest);

    @PutMapping("v1/credit-conveyor/offers")
    void updateOfferStatus(@RequestParam Long conveyorId,
                           @RequestBody ConveyorUpdateOfferRequest offerRequest);
}