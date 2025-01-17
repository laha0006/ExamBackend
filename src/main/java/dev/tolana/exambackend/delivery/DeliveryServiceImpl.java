package dev.tolana.exambackend.delivery;

import dev.tolana.exambackend.delivery.dto.*;
import dev.tolana.exambackend.delivery.exception.DeliveryDroneAlreadyAssignedException;
import dev.tolana.exambackend.delivery.exception.DeliveryNeedsDroneToFinishException;
import dev.tolana.exambackend.delivery.exception.DeliveryNotFoundException;
import dev.tolana.exambackend.delivery.model.Delivery;
import dev.tolana.exambackend.delivery.model.ScheduleOption;
import dev.tolana.exambackend.drone.model.Drone;
import dev.tolana.exambackend.drone.DroneService;
import dev.tolana.exambackend.drone.model.OperationStatus;
import dev.tolana.exambackend.drone.exception.DroneNotFoundException;
import dev.tolana.exambackend.drone.exception.DroneNotInServiceException;
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
    private final DeliveryRequestMapper deliveryRequestMapper;
    private final DeliveryToDtoMapper deliveryToDtoMapper;

    @Override
    public List<DeliveryDto> getAllNonDeliveredDeliveries() {
        return deliveryRepository.findByActualDeliveryTimeIsNullOrderByEstimatedDeliveryTime()
                .stream()
                .map(deliveryToDtoMapper)
                .toList();
    }

    @Override
    public Delivery addDelivery(DeliveryRequest deliveryRequest) {
        Delivery delivery = deliveryRequestMapper.apply(deliveryRequest);
        deliveryRepository.save(delivery);
        return delivery;
    }

    @Override
    public List<DeliveryDto> getAllDeliveriesWithNoDrone() {
        return deliveryRepository.findByDroneIsNull().stream().map(deliveryToDtoMapper).toList();
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
            throw new DeliveryNotFoundException("Delivery does not exist");
        }
        if (optionalDrone.isEmpty()) {
            throw new DroneNotFoundException("Drone does not exist");
        }

        Delivery delivery = optionalDelivery.get();
        Drone drone = optionalDrone.get();

        if (delivery.getDrone() != null) {
            throw new DeliveryDroneAlreadyAssignedException("Drone already assigned!");
        }
        if(drone.getStatus() != OperationStatus.IN_SERVICE) {
            throw new DroneNotInServiceException("Drone's status needs to be IN_SERVICE but was:  " + drone.getStatus().toString());
        }

        delivery.setDrone(drone);
        deliveryRepository.save(delivery);
    }

    @Override
    public void finsihdelivery(long id) {
        System.out.println("finishdelivery LOL?");
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);
        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();
            Drone drone = delivery.getDrone();
            if (drone == null) {
                throw new DeliveryNeedsDroneToFinishException("You cannot finish a delivery without a drone!");
            }
            System.out.println("AFTER EXCEPTION THROWN???");
            delivery.setActualDeliveryTime(LocalDateTime.now());
            deliveryRepository.save(delivery);
        }
    }

    @Override
    public List<DeliveryDto> getAllCompletedDeliveries() {
        return deliveryRepository.findByActualDeliveryTimeIsNotNullOrderByActualDeliveryTime()
                .stream()
                .map(deliveryToDtoMapper).toList();
    }
}
