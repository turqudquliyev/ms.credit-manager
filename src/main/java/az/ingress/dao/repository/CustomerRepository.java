package az.ingress.dao.repository;

import az.ingress.dao.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByPin(String pin);

    boolean existsByPinOrPhoneNumber(String pin, String phoneNumber);

    Optional<CustomerEntity> findById(Long id);
}