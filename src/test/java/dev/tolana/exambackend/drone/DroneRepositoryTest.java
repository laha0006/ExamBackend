package dev.tolana.exambackend.drone;

import dev.tolana.exambackend.delivery.Delivery;
import dev.tolana.exambackend.delivery.DeliveryRepository;
import dev.tolana.exambackend.pizza.Pizza;
import dev.tolana.exambackend.pizza.PizzaRepository;
import dev.tolana.exambackend.station.Station;
import dev.tolana.exambackend.station.StationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class DroneRepositoryTest {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @BeforeEach
    void setUp() {
        Station stationOne = Station.builder()
                .latitude(55.43)
                .longitude(12.35)
                .build();

        Station stationTwo = Station.builder()
                .latitude(55.40)
                .longitude(12.32)
                .build();

        Station stationThree = Station.builder()
                .latitude(55.42)
                .longitude(12.33)
                .build();

        Station stationFour = Station.builder()
                .latitude(55.41)
                .longitude(12.34)
                .build();

        stationRepository.save(stationOne);
        stationRepository.save(stationTwo);
        stationRepository.save(stationThree);
        stationRepository.save(stationFour);

        Drone droneOne = Drone.builder()
                .uuid(UUID.randomUUID().toString())
                .status(OperationStatus.OUT_OF_SERVICE)
                .station(stationOne)
                .build();

        Drone droneTwo = Drone.builder()
                .uuid(UUID.randomUUID().toString())
                .status(OperationStatus.IN_SERVICE)
                .station(stationTwo)
                .build();

        Drone droneThree = Drone.builder()
                .uuid(UUID.randomUUID().toString())
                .status(OperationStatus.IN_SERVICE)
                .station(stationThree)
                .build();

        droneRepository.save(droneOne);
        droneRepository.save(droneTwo);
        droneRepository.save(droneThree);

        Pizza pizzaOne = Pizza.builder()
                .price(65)
                .title("Margherita")
                .build();

        Pizza pizzaTwo = Pizza.builder()
                .price(70)
                .title("Peperoni")
                .build();

        Pizza pizzaThree = Pizza.builder()
                .price(75)
                .title("Hawaiian")
                .build();

        Pizza pizzaFour = Pizza.builder()
                .price(89)
                .title("Meat Lover")
                .build();


        pizzaRepository.save(pizzaOne);
        pizzaRepository.save(pizzaTwo);
        pizzaRepository.save(pizzaThree);
        pizzaRepository.save(pizzaFour);

        Delivery delivery = Delivery.builder()
                .pizza(pizzaOne)
                .address("Home")
                .estimatedDeliveryTime(LocalDateTime.now().plusMinutes(30))
                .drone(droneOne)
                .build();

        Delivery deliveryTwo = Delivery.builder()
                .pizza(pizzaOne)
                .address("Home")
                .estimatedDeliveryTime(LocalDateTime.now().plusMinutes(35))
                .drone(droneTwo)
                .build();

        deliveryRepository.save(delivery);
        deliveryRepository.save(deliveryTwo);
    }

    @Test
    void testFindAll() {
        List<Drone> drones = droneRepository.findAll();
        System.out.println("######### DRONES: " + drones);
        assertNotNull(drones);
        assertEquals(3, drones.size());
    }

    @Test
    void testFindDroneWithFewestDeliveries() {
        Drone drone = droneRepository.findDroneWithFewestDeliveries();
        assertNotNull(drone);
        assertEquals(drone.getId(),3L); // drone one and two, is attached to a delivery, so we expected drone 3.
    }


}