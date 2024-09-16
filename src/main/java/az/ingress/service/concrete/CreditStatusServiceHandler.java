package az.ingress.service.concrete;

import az.ingress.annotation.Log;
import az.ingress.dao.entity.CreditEntity;
import az.ingress.dao.repository.CreditRepository;
import az.ingress.model.enums.CreditStatus;
import az.ingress.service.abstraction.CreditStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.ingress.mapper.CreditMapper.CREDIT_MAPPER;
import static az.ingress.mapper.StatusHistoryMapper.STATUS_HISTORY_MAPPER;

@Log
@Service
@RequiredArgsConstructor
public class CreditStatusServiceHandler implements CreditStatusService {
    private final CreditRepository creditRepository;

    public void updateCreditStatus(CreditEntity creditEntity, CreditStatus status) {
        var statusHistory = STATUS_HISTORY_MAPPER.buildStatusHistoryEntity(status, creditEntity);
        CREDIT_MAPPER.setStatus(creditEntity, status);
        CREDIT_MAPPER.setStatusHistoryRelation(creditEntity, statusHistory);
    }
}