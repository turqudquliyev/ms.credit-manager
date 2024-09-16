package az.ingress.service.abstraction;

import az.ingress.dao.entity.CustomerEntity;
import az.ingress.model.request.CreateCustomerRequest;
import az.ingress.model.response.CustomerResponse;

public interface CustomerService {

    void saveCustomer(CreateCustomerRequest customerRequest);

    CustomerResponse getCustomerByPin(String pin);

    CustomerEntity fetchCustomerIfExist(Long id);
}