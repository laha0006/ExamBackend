package dev.tolana.exambackend.drone;

import java.util.List;
import java.util.Optional;

public interface DroneService {

    List<Drone> getAllDrones();

    Optional<Drone> getDrone(long id);

    Drone addDrone();

    Drone enableDrone(long id);

    Drone disableDrone(long id);

    Drone retireDrone(long id);

    Drone getDroneWithFewestDeliveries();
}
