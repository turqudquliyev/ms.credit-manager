package az.ingress.dao.repository;

import az.ingress.dao.entity.OfferEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferRepository extends CrudRepository<OfferEntity, Long> {

    List<OfferEntity> findAllByCreditId(Long creditId);
}