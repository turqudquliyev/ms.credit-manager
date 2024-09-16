package az.ingress.dao.repository;

import az.ingress.dao.entity.CreditEntity;
import az.ingress.model.enums.CreditStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CreditRepository extends CrudRepository<CreditEntity, Long> {

    @EntityGraph(attributePaths = "statusHistories")
    Optional<CreditEntity> findWithStatusHistoriesById(@NonNull Long id);

    @EntityGraph(attributePaths = "offers")
    Optional<CreditEntity> findWithOffersByIdAndStatus(@NonNull Long id, CreditStatus status);

    List<CreditEntity> findAllByCheckDateBeforeAndStatus(LocalDateTime checkDate, CreditStatus status);
}