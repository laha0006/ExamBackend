package dev.tolana.exambackend.drone;

import dev.tolana.exambackend.drone.dto.DroneDto;
import dev.tolana.exambackend.drone.dto.DroneToDtoMapper;
import dev.tolana.exambackend.drone.exception.NoStationException;
import dev.tolana.exambackend.drone.model.Drone;
import dev.tolana.exambackend.drone.model.OperationStatus;
import dev.tolana.exambackend.station.Station;
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
    public DroneDto addDrone() {
        Optional<Station> optionalStation = stationService.getStationWithFewestDrones();
        if (optionalStation.isEmpty()) {
            throw new NoStationException("You cannot create a drone without a station!");
        }
        Station station = optionalStation.get();
        Drone drone = Drone.builder()
                .uuid(UUID.randomUUID().toString())
                .status(OperationStatus.IN_SERVICE)
                .station(station)
                .build();
        droneRepository.save(drone);
        return droneToDtoMapper.apply(drone);
    }

    @Override
    public DroneDto enableDrone(long id) {
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if (droneOptional.isPresent()) {
            Drone drone = droneOptional.get();
            drone.setStatus(OperationStatus.IN_SERVICE);
            droneRepository.save(drone);
            return droneToDtoMapper.apply(drone);
        }
        return null;

    }

    @Override
    public DroneDto disableDrone(long id) {
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if (droneOptional.isPresent()) {
            Drone drone = droneOptional.get();
            drone.setStatus(OperationStatus.OUT_OF_SERVICE);
            droneRepository.save(drone);
            return droneToDtoMapper.apply(drone);
        }
        return null;
    }

    @Override
    public DroneDto retireDrone(long id) {
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if (droneOptional.isPresent()) {
            Drone drone = droneOptional.get();
            drone.setStatus(OperationStatus.DECOMMISSIONED);
            droneRepository.save(drone);
            return droneToDtoMapper.apply(drone);
        }
        return null;
    }

    @Override
    public Drone getDroneWithFewestDeliveries() {
        return droneRepository.findDroneWithFewestDeliveries();
    }
}
