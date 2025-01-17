package dev.tolana.exambackend.drone.dto;

import dev.tolana.exambackend.drone.model.Drone;
import dev.tolana.exambackend.station.dto.StationToDtoWithoutDronesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class DroneToDtoMapper implements Function<Drone, DroneDto> {

    private final StationToDtoWithoutDronesMapper stationToDtoMapper;

    @Override
    public DroneDto apply(Drone drone) {
        return new DroneDto(
                drone.getId(),
                drone.getUuid(),
                drone.getStatus(),
                stationToDtoMapper.apply(drone.getStation())
        );
    }
}
