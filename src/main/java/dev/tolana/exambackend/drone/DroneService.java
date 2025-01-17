package dev.tolana.exambackend.drone;

import dev.tolana.exambackend.drone.dto.DroneDto;

import java.util.List;
import java.util.Optional;

public interface DroneService {

    List<DroneDto> getAllDrones();

    Optional<Drone> getDrone(long id);

    DroneDto addDrone();

    DroneDto enableDrone(long id);

    DroneDto disableDrone(long id);

    DroneDto retireDrone(long id);

    Drone getDroneWithFewestDeliveries();
}
