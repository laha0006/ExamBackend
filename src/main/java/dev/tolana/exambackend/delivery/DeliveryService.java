package dev.tolana.exambackend.delivery;

import dev.tolana.exambackend.delivery.dto.DeliveryRequest;
import dev.tolana.exambackend.delivery.dto.ScheduleRequest;

import java.util.List;

public interface DeliveryService {
    List<Delivery> getAllNonDeliveredDeliveries();

    Delivery addDelivery(DeliveryRequest deliveryRequest);

    List<Delivery> getAllDeliveriesWithNoDrone();

    void scheduleDelivery(ScheduleRequest scheduleRequest);

    void scheduleDeliverySmart(long deliveryId);
    void scheduleDeliveryManual(long deliveryId, long droneId);

    void finsihdelivery(long id);
}
