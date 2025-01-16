package dev.tolana.exambackend.drone;

import dev.tolana.exambackend.drone.dto.DroneDto;

import java.util.List;
import java.util.Optional;

public interface DroneService {

    List<DroneDto> getAllDrones();

    Optional<Drone> getDrone(long id);

    Drone addDrone();

    Drone enableDrone(long id);

    Drone disableDrone(long id);

    Drone retireDrone(long id);

    Drone getDroneWithFewestDeliveries();
}
