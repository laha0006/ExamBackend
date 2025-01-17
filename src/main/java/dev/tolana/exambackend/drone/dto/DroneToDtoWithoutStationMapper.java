package dev.tolana.exambackend.drone.dto;

import dev.tolana.exambackend.drone.model.Drone;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DroneToDtoWithoutStationMapper implements Function<Drone, DroneDtoWithoutStation> {
    @Override
    public DroneDtoWithoutStation apply(Drone drone) {
        return new DroneDtoWithoutStation(
                drone.getId(),
                drone.getUuid(),
                drone.getStatus()
        );
    }
}
