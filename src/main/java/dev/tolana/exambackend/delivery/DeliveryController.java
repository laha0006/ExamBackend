package dev.tolana.exambackend.delivery;

import dev.tolana.exambackend.delivery.dto.DeliveryDto;
import dev.tolana.exambackend.delivery.dto.DeliveryRequest;
import dev.tolana.exambackend.delivery.dto.ScheduleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/deliveries")
    public ResponseEntity<List<DeliveryDto>> getDeliveries() {
        return ResponseEntity.ok(deliveryService.getAllNonDeliveredDeliveries());
    }

    @PostMapping("/deliveries/add")
    public ResponseEntity<Delivery> addDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        return ResponseEntity.status(201).body(deliveryService.addDelivery(deliveryRequest));
    }

    @GetMapping("/deliveries/queue")
    public ResponseEntity<List<DeliveryDto>> getDeliveriesQueue() {
        return ResponseEntity.ok(deliveryService.getAllDeliveriesWithNoDrone());
    }

    @PostMapping("/deliveries/schedule")
    public ResponseEntity<Void> scheduleDelivery(@RequestBody ScheduleRequest scheduleRequest) {
        deliveryService.scheduleDelivery(scheduleRequest);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/deliveries/finish")
    public ResponseEntity<Void> finishDelivery(@RequestParam long id) {
        deliveryService.finsihdelivery(id);
        return ResponseEntity.ok().build();
    }
}
