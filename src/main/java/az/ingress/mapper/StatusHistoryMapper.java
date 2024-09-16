package az.ingress.mapper;

import az.ingress.dao.entity.CreditEntity;
import az.ingress.dao.entity.StatusHistoryEntity;
import az.ingress.model.dto.StatusHistoryDto;
import az.ingress.model.enums.CreditStatus;

import java.util.List;

public enum StatusHistoryMapper {
    STATUS_HISTORY_MAPPER;

    public StatusHistoryEntity buildStatusHistoryEntity(CreditStatus status, CreditEntity creditEntity) {
        return StatusHistoryEntity.builder()
                                  .status(status)
                                  .credit(creditEntity)
                                  .build();
    }

    public List<StatusHistoryDto> buildStatusHistoryDtoList(List<StatusHistoryEntity> statusHistories) {
        return statusHistories.stream()
                              .map(STATUS_HISTORY_MAPPER::toStatusHistoryDto)
                              .toList();
    }

    private StatusHistoryDto toStatusHistoryDto(StatusHistoryEntity statusHistory) {
        return StatusHistoryDto.builder()
                               .id(statusHistory.getId())
                               .status(statusHistory.getStatus())
                               .build();
    }
}