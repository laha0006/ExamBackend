package dev.tolana.exambackend.delivery.dto;

import dev.tolana.exambackend.delivery.model.Delivery;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

@Component
public class DeliveryRequestMapper implements Function<DeliveryRequest, Delivery> {

    @Override
    public Delivery apply(DeliveryRequest deliveryRequest) {
        return Delivery.builder()
                .estimatedDeliveryTime(LocalDateTime.now().plusMinutes(30))
                .address(deliveryRequest.address())
                .pizza(deliveryRequest.pizza())
                .build();
    }
}
