package dev.tolana.exambackend.delivery.dto;

import dev.tolana.exambackend.pizza.Pizza;

public record DeliveryRequest(Pizza pizza, String address) {
}
