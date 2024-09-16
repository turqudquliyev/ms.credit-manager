package az.ingress.service.abstraction;

import az.ingress.dao.entity.CreditEntity;
import az.ingress.model.enums.CreditStatus;

public interface CreditStatusService {

    void updateCreditStatus(CreditEntity creditEntity, CreditStatus status);
}