package dev.tolana.exambackend.drone;

import dev.tolana.exambackend.drone.dto.DroneDto;
import dev.tolana.exambackend.drone.dto.DroneToDtoMapper;
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
    private final DroneToDtoMapper droneToDtoMapper;

    @Override
    public List<DroneDto> getAllDrones() {
        return droneRepository.findAll().stream()
                .map(droneToDtoMapper)
                .toList();
    }

    @Override
    public Optional<Drone> getDrone(long id) {
        return droneRepository.findById(id);
    }

    @Override
    public Drone addDrone() {
        Drone drone = Drone.builder()
                .uuid(UUID.randomUUID().toString())
                .status(OperationStatus.IN_SERVICE)
                .station(stationService.getStationWithFewestDrones())
                .build();
        droneRepository.save(drone);
        return drone;
    }

    @Override
    public Drone enableDrone(long id) {
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if (droneOptional.isPresent()) {
            Drone drone = droneOptional.get();
            drone.setStatus(OperationStatus.IN_SERVICE);
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
            drone.setStatus(OperationStatus.OUT_OF_SERVICE);
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
            drone.setStatus(OperationStatus.DECOMMISSIONED);
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
