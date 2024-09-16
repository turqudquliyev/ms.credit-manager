package az.ingress.mapper;

import az.ingress.dao.entity.CustomerEntity;
import az.ingress.model.request.CreateCustomerRequest;
import az.ingress.model.response.CustomerResponse;

public enum CustomerMapper {
    CUSTOMER_MAPPER;

    public CustomerEntity toCustomerEntity(CreateCustomerRequest customerRequest) {
        return CustomerEntity.builder()
                             .pin(customerRequest.getPin())
                             .fullName(customerRequest.getFullName())
                             .phoneNumber(customerRequest.getPhoneNumber())
                             .build();
    }

    public CustomerResponse toCustomerResponse(CustomerEntity customerEntity) {
        return CustomerResponse.builder()
                               .id(customerEntity.getId())
                               .pin(customerEntity.getPin())
                               .fullName(customerEntity.getFullName())
                               .phoneNumber(customerEntity.getPhoneNumber())
                               .build();
    }
}