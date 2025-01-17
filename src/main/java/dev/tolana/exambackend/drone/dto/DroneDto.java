package dev.tolana.exambackend.drone.dto;

import dev.tolana.exambackend.drone.model.OperationStatus;
import dev.tolana.exambackend.station.dto.StationDtoWithoutDrones;

public record DroneDto(
        Long id,
        String uuid,
        OperationStatus status,
        StationDtoWithoutDrones station
) {
}
