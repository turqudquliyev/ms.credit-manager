package az.ingress.controller;

import az.ingress.model.request.CreateCustomerRequest;
import az.ingress.model.response.CustomerResponse;
import az.ingress.service.abstraction.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createCustomer(@RequestBody @Valid CreateCustomerRequest customerRequest) {
        customerService.saveCustomer(customerRequest);
    }

    @GetMapping
    public CustomerResponse getCustomerByPin(@RequestParam String pin) {
        return customerService.getCustomerByPin(pin);
    }
}