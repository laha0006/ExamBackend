package dev.tolana.exambackend.delivery.dto;

import dev.tolana.exambackend.drone.Drone;
import dev.tolana.exambackend.drone.dto.DroneDtoWithoutStation;
import dev.tolana.exambackend.pizza.Pizza;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record DeliveryDto(long id,
                          String address,
                          LocalDateTime estimatedDeliveryTime,
                          LocalDateTime actualDeliveryTime,
                          Pizza pizza,
                          DroneDtoWithoutStation drone) {
}
