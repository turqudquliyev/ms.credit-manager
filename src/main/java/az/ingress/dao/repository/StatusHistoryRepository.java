package az.ingress.dao.repository;

import az.ingress.dao.entity.StatusHistoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface StatusHistoryRepository extends CrudRepository<StatusHistoryEntity, Long> {}