package dev.tolana.exambackend.delivery.dto;

import dev.tolana.exambackend.delivery.model.ScheduleOption;

public record ScheduleRequest(long deliveryId, long droneId, ScheduleOption option) {
}
