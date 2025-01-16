package dev.tolana.exambackend.delivery;

import dev.tolana.exambackend.delivery.dto.DeliveryRequest;
import dev.tolana.exambackend.delivery.dto.ScheduleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/deliveries")
    public List<Delivery> getDeliveries() {
        return deliveryService.getAllNonDeliveredDeliveries();
    }

    @PostMapping("/deliveries/add")
    public void addDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        deliveryService.addDelivery(deliveryRequest);
    }

    @GetMapping("/deliveries/queue")
    public List<Delivery> getDeliveriesQueue() {
        return deliveryService.getAllDeliveriesWithNoDrone();
    }

    @PostMapping("/deliveries/schedule")
    public void scheduleDelivery(@RequestBody ScheduleRequest scheduleRequest) {
        deliveryService.scheduleDelivery(scheduleRequest);
    }

    @GetMapping("/deliveries/{id}/finish")
    public void finishDelivery(@PathVariable long id) {
        deliveryService.finsihDelivery(id);
    }
}
