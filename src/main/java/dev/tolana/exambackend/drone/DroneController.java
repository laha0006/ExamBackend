package dev.tolana.exambackend.drone;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class DroneController {

    private final DroneService droneService;

    @GetMapping("/drones")
    public List<Drone> getDrones() {
        return droneService.getAllDrones();
    }

    @GetMapping("/drones/{id}/enable")
    public ResponseEntity<Drone> enableDrone(@PathVariable long id) {
        return ResponseEntity.ok(droneService.enableDrone(id));
    }

    @GetMapping("/drones/{id}/disable")
    public ResponseEntity<Drone> disableDrone(@PathVariable long id) {
        return ResponseEntity.ok(droneService.disableDrone(id));
    }

    @GetMapping("/drones/{id}/retire")
    public ResponseEntity<Drone> retireDrone(@PathVariable long id) {
        return ResponseEntity.ok(droneService.retireDrone(id));
    }

    @GetMapping("/drones/add")
    public ResponseEntity<Object> addDrone() {
        System.out.println("## ADD NEW DRONE ##");
        droneService.addDrone();
        return ResponseEntity.ok().build();
    }

}
