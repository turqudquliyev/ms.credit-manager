package az.ingress.controller;

import az.ingress.model.request.InitializeCreditRequest;
import az.ingress.model.response.CreditResponse;
import az.ingress.service.abstraction.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/credits")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void initializeCredit(@RequestBody @Valid InitializeCreditRequest creditRequest) {
        creditService.initializeCredit(creditRequest);
    }

    @GetMapping("{id}")
    public CreditResponse getCreditById(@PathVariable Long id) {
        return creditService.getCreditById(id);
    }

    @PatchMapping("{id}/accept")
    @ResponseStatus(NO_CONTENT)
    public void acceptCredit(@PathVariable Long id, @RequestParam Long offerId) {
        creditService.acceptCredit(id, offerId);
    }

    @PatchMapping("{id}/reject")
    @ResponseStatus(NO_CONTENT)
    public void rejectCredit(@PathVariable Long id) {
        creditService.rejectCredit(id);
    }
}