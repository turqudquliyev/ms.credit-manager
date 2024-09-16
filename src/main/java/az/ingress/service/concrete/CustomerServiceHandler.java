package az.ingress.service.concrete;

import az.ingress.annotation.Log;
import az.ingress.dao.entity.CustomerEntity;
import az.ingress.dao.repository.CustomerRepository;
import az.ingress.exception.AlreadyExistsException;
import az.ingress.exception.NotFoundException;
import az.ingress.model.request.CreateCustomerRequest;
import az.ingress.model.response.CustomerResponse;
import az.ingress.service.abstraction.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.ingress.exception.ErrorMessage.CUSTOMER_ALREADY_EXISTS;
import static az.ingress.exception.ErrorMessage.CUSTOMER_NOT_FOUND;
import static az.ingress.mapper.CustomerMapper.CUSTOMER_MAPPER;

@Log
@Service
@RequiredArgsConstructor
public class CustomerServiceHandler implements CustomerService {
    private final CustomerRepository customerRepository;

    public void saveCustomer(CreateCustomerRequest customerRequest) {
        ensureCustomerDoesNotExist(customerRequest.getPin(), customerRequest.getPhoneNumber());
        var customerEntity = CUSTOMER_MAPPER.toCustomerEntity(customerRequest);
        customerRepository.save(customerEntity);
    }

    public CustomerResponse getCustomerByPin(String pin) {
        var customerEntity = fetchCustomerIfExist(pin);
        return CUSTOMER_MAPPER.toCustomerResponse(customerEntity);
    }

    public CustomerEntity fetchCustomerIfExist(Long id) {
        return customerRepository.findById(id)
                                 .orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND.getCode()));
    }

    private void ensureCustomerDoesNotExist(String pin, String phoneNumber) {
        if (customerRepository.existsByPinOrPhoneNumber(pin, phoneNumber))
            throw new AlreadyExistsException(CUSTOMER_ALREADY_EXISTS.getCode());
    }

    private CustomerEntity fetchCustomerIfExist(String pin) {
        return customerRepository.findByPin(pin)
                                 .orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND.getCode()));
    }
}