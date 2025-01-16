package dev.tolana.exambackend.drone.dto;

import dev.tolana.exambackend.drone.OperationStatus;
import dev.tolana.exambackend.station.Station;
import dev.tolana.exambackend.station.dto.StationDto;
import dev.tolana.exambackend.station.dto.StationDtoWithoutDrones;

public record DroneDto(
        Long id,
        String uuid,
        OperationStatus status,
        StationDtoWithoutDrones station
) {
}
