package dev.tolana.exambackend.station;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationService {
    List<Station> getAllStations();


    Station getStationWithFewestDrones();
}
