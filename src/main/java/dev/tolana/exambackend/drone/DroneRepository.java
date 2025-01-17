package dev.tolana.exambackend.drone;

import dev.tolana.exambackend.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    @Query(value = "SELECT d.*, COUNT(dl.id) FROM drone d LEFT JOIN delivery dl ON d.id = dl.drone_id WHERE d.station_id != 2 GROUP BY d.id ORDER BY COUNT(dl.id) LIMIT 1", nativeQuery = true)
    Drone findDroneWithFewestDeliveries();
}
