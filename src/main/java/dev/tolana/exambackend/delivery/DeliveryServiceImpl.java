package dev.tolana.exambackend.delivery;

import dev.tolana.exambackend.delivery.dto.DeliveryRequest;
import dev.tolana.exambackend.delivery.dto.ScheduleRequest;
import dev.tolana.exambackend.drone.Drone;
import dev.tolana.exambackend.drone.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DroneService droneService;

    @Override
    public List<Delivery> getAllNonDeliveredDeliveries() {
        return deliveryRepository.findByActualDeliveryTimeIsNullOrderByEstimatedDeliveryTime();
    }

    @Override
    public void addDelivery(DeliveryRequest deliveryRequest) {
        Delivery delivery = Delivery.builder()
                .estimatedDeliveryTime(LocalDateTime.now().plusMinutes(30))
                .address(deliveryRequest.address())
                .pizza(deliveryRequest.pizza())
                .build();
        deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> getAllDeliveriesWithNoDrone() {
        return deliveryRepository.findByDroneIsNull();
    }

    @Override
    public void scheduleDelivery(ScheduleRequest scheduleRequest) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(scheduleRequest.deliveryId());
        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();
            delivery.setDrone(droneService.getDroneWithFewestDeliveries());
            deliveryRepository.save(delivery);
        }
    }

    @Override
    public void finsihDelivery(long id) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);
        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();
            Drone drone = delivery.getDrone();
            if (drone == null) {
                System.out.println("WE FAILED");
                return;
            }
            delivery.setActualDeliveryTime(LocalDateTime.now());
            deliveryRepository.save(delivery);
        }
    }
}
