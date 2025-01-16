package dev.tolana.exambackend.delivery.dto;

import dev.tolana.exambackend.delivery.ScheduleOption;

public record ScheduleRequest(long deliveryId, long droneId, ScheduleOption option) {
}
