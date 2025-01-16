package dev.tolana.exambackend.delivery;

import dev.tolana.exambackend.delivery.dto.DeliveryRequest;
import dev.tolana.exambackend.delivery.dto.ScheduleRequest;
import dev.tolana.exambackend.drone.Drone;
import dev.tolana.exambackend.drone.DroneService;
import dev.tolana.exambackend.drone.OperationStatus;
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
        ScheduleOption option = scheduleRequest.option();
        switch(option) {
            case ScheduleOption.SMART -> scheduleDeliverySmart(scheduleRequest.deliveryId());
            case ScheduleOption.MANUAL -> scheduleDeliveryManual(scheduleRequest.deliveryId(), scheduleRequest.droneId());
        }
    }

    @Override
    public void scheduleDeliverySmart(long deliveryId) {
        System.out.println("SMART DRONE ASSIGNMENT");

        Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();
            delivery.setDrone(droneService.getDroneWithFewestDeliveries());
            deliveryRepository.save(delivery);
        }
    }

    @Override
    public void scheduleDeliveryManual(long deliveryId, long droneId) {
        System.out.println("MANUAL DRONE ASSIGNMENT");

        Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
        Optional<Drone> optionalDrone = droneService.getDrone(droneId);

        if (optionalDelivery.isEmpty()) {
            throw new RuntimeException("Delivery does not exist");
        }
        if (optionalDrone.isEmpty()) {
            throw new RuntimeException("Drone does not exist");
        }

        Delivery delivery = optionalDelivery.get();
        Drone drone = optionalDrone.get();

        if (delivery.getDrone() != null) {
            throw new RuntimeException("Drone already assigned!");
        }
        if(drone.getStatus() != OperationStatus.IN_OPERATION) {
            throw new RuntimeException("Drone's status is not IN_OPERATION: " + drone.getStatus().toString());
        }

        delivery.setDrone(drone);
        deliveryRepository.save(delivery);
    }

    @Override
    public void finsihdelivery(long id) {
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
