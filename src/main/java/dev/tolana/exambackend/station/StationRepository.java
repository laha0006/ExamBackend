package dev.tolana.exambackend.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {

    @Query(value = "SELECT s.* FROM station s LEFT JOIN drone d ON s.id = d.station_id GROUP BY s.id ORDER BY COUNT(d.id) ASC LIMIT 1", nativeQuery = true)
    Optional<Station> getStationWithFewestDronesNative();

}
