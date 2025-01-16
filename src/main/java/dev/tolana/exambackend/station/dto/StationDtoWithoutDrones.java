package dev.tolana.exambackend.station.dto;

import dev.tolana.exambackend.drone.dto.DroneDtoWithoutStation;

import java.util.List;

public record StationDtoWithoutDrones(long id,
                                      double latitude,
                                      double longitude) {
}
