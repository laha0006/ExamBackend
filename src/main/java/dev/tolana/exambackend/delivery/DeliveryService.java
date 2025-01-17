package dev.tolana.exambackend.delivery;

import dev.tolana.exambackend.delivery.dto.DeliveryDto;
import dev.tolana.exambackend.delivery.dto.DeliveryRequest;
import dev.tolana.exambackend.delivery.dto.ScheduleRequest;
import dev.tolana.exambackend.delivery.model.Delivery;

import java.util.List;

public interface DeliveryService {
    List<DeliveryDto> getAllNonDeliveredDeliveries();

    Delivery addDelivery(DeliveryRequest deliveryRequest);

    List<DeliveryDto> getAllDeliveriesWithNoDrone();

    void scheduleDelivery(ScheduleRequest scheduleRequest);

    void scheduleDeliverySmart(long deliveryId);
    void scheduleDeliveryManual(long deliveryId, long droneId);

    void finsihdelivery(long id);

    List<DeliveryDto> getAllCompletedDeliveries();
}
