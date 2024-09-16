package az.ingress.controller;

import az.ingress.model.response.OfferResponse;
import az.ingress.service.abstraction.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/offers")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @GetMapping
    public List<OfferResponse> getOffersByCreditId(@RequestParam Long creditId) {
        return offerService.getOffersByCreditId(creditId);
    }
}