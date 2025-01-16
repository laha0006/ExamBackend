package dev.tolana.exambackend.drone;

import dev.tolana.exambackend.station.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final StationService stationService;

    @Override
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @Override
    public void addDrone() {
        Drone drone = Drone.builder()
                .uuid(UUID.randomUUID().toString())
                .status(OperationStatus.IN_OPERATION)
                .station(stationService.getStationWithFewestDrones())
                .build();
        droneRepository.save(drone);
    }

    @Override
    public Drone enableDrone(long id) {
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if (droneOptional.isPresent()) {
            Drone drone = droneOptional.get();
            drone.setStatus(OperationStatus.IN_OPERATION);
            droneRepository.save(drone);
            return drone;
        }
        return null;

    }

    @Override
    public Drone disableDrone(long id) {
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if (droneOptional.isPresent()) {
            Drone drone = droneOptional.get();
            drone.setStatus(OperationStatus.OUT_OF_OPERATION);
            droneRepository.save(drone);
            return drone;
        }
        return null;
    }

    @Override
    public Drone retireDrone(long id) {
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if (droneOptional.isPresent()) {
            Drone drone = droneOptional.get();
            drone.setStatus(OperationStatus.PHASED_OUT);
            droneRepository.save(drone);
            return drone;
        }
        return null;
    }

    @Override
    public Drone getDroneWithFewestDeliveries() {
        return droneRepository.findDroneWithFewestDeliveries();
    }
}
