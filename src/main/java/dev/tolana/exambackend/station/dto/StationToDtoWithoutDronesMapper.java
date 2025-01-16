package dev.tolana.exambackend.station.dto;

import dev.tolana.exambackend.station.Station;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StationToDtoWithoutDronesMapper implements Function<Station, StationDtoWithoutDrones> {
    @Override
    public StationDtoWithoutDrones apply(Station station) {
        return new StationDtoWithoutDrones(
                station.getId(),
                station.getLatitude(),
                station.getLongitude()
        );
    }
}
