package dev.tolana.exambackend.station.dto;

import dev.tolana.exambackend.drone.dto.DroneToDtoWithoutStationMapper;
import dev.tolana.exambackend.station.Station;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class StationToDtoMapper implements Function<Station, StationDto> {

    private final DroneToDtoWithoutStationMapper droneToDtoWithoutStation;

    @Override
    public StationDto apply(Station station) {
        return new StationDto(
                station.getId(),
                station.getLatitude(),
                station.getLongitude(),
                station.getDrones().stream().map(droneToDtoWithoutStation).toList()
        );
    }
}
