package dev.tolana.exambackend.station;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StationService {
    List<Station> getAllStations();


    Optional<Station> getStationWithFewestDrones();
}
