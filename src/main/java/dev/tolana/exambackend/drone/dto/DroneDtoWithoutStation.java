package dev.tolana.exambackend.drone.dto;

import dev.tolana.exambackend.drone.OperationStatus;
import dev.tolana.exambackend.station.dto.StationDto;

public record DroneDtoWithoutStation(
        Long id,
        String uuid,
        OperationStatus status
) {
}
