package dev.tolana.exambackend.delivery;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByActualDeliveryTimeIsNullOrderByEstimatedDeliveryTime();
    List<Delivery> findByDroneIsNull();
    List<Delivery> findByActualDeliveryTimeIsNotNullOrderByActualDeliveryTime();
}
