package dev.tolana.exambackend.delivery;

import dev.tolana.exambackend.delivery.dto.DeliveryRequest;
import dev.tolana.exambackend.delivery.dto.ScheduleRequest;

import java.util.List;

public interface DeliveryService {
    List<Delivery> getAllNonDeliveredDeliveries();

    void addDelivery(DeliveryRequest deliveryRequest);

    List<Delivery> getAllDeliveriesWithNoDrone();

    void scheduleDelivery(ScheduleRequest scheduleRequest);

    void finsihDelivery(long id);
}
