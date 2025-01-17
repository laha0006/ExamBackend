package dev.tolana.exambackend.drone.dto;

import dev.tolana.exambackend.drone.model.OperationStatus;

public record DroneDtoWithoutStation(
        Long id,
        String uuid,
        OperationStatus status
) {
}
