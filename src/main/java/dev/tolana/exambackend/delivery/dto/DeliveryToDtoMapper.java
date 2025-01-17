package dev.tolana.exambackend.delivery.dto;

import dev.tolana.exambackend.delivery.model.Delivery;
import dev.tolana.exambackend.drone.dto.DroneToDtoWithoutStationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class DeliveryToDtoMapper implements Function<Delivery, DeliveryDto> {

    private final DroneToDtoWithoutStationMapper droneToDtoWithoutStation;

    @Override
    public DeliveryDto apply(Delivery delivery) {
        return new DeliveryDto(
                delivery.getId(),
                delivery.getAddress(),
                delivery.getEstimatedDeliveryTime(),
                delivery.getActualDeliveryTime(),
                delivery.getPizza(),
                delivery.getDrone() != null ? droneToDtoWithoutStation.apply(delivery.getDrone()) : null
        );
    }
}
