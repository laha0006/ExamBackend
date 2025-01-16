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
    public ResponseEntity<List<Drone>> getDrones() {
        return ResponseEntity.ok(droneService.getAllDrones());
    }

    @GetMapping("/drones/enable")
    public ResponseEntity<Drone> enableDrone(@RequestParam long id) {
        return ResponseEntity.ok(droneService.enableDrone(id));
    }

    @GetMapping("/drones/disable")
    public ResponseEntity<Drone> disableDrone(@RequestParam long id) {
        return ResponseEntity.ok(droneService.disableDrone(id));
    }

    @GetMapping("/drones/retire")
    public ResponseEntity<Drone> retireDrone(@RequestParam long id) {
        return ResponseEntity.ok(droneService.retireDrone(id));
    }

    @GetMapping("/drones/add")
    public ResponseEntity<Drone> addDrone() {
        System.out.println("## ADD NEW DRONE ##");
        Drone drone = droneService.addDrone();
        return ResponseEntity.status(201).body(drone);
    }

}
