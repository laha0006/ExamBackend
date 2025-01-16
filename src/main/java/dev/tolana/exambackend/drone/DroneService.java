package dev.tolana.exambackend.drone;

import java.util.List;

public interface DroneService {

    List<Drone> getAllDrones();

    void addDrone();

    Drone enableDrone(long id);

    Drone disableDrone(long id);

    Drone retireDrone(long id);

    Drone getDroneWithFewestDeliveries();
}
