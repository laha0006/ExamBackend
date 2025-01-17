package dev.tolana.exambackend.drone;

import dev.tolana.exambackend.delivery.model.Delivery;
import dev.tolana.exambackend.delivery.DeliveryRepository;
import dev.tolana.exambackend.drone.model.Drone;
import dev.tolana.exambackend.drone.model.OperationStatus;
import dev.tolana.exambackend.pizza.Pizza;
import dev.tolana.exambackend.pizza.PizzaRepository;
import dev.tolana.exambackend.station.Station;
import dev.tolana.exambackend.station.StationRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DroneRepositoryTest {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {

        // Clear and flush the persistence context before each test
        entityManager.flush();  // Ensure all changes are persisted
        entityManager.clear();  // Clear the persistence context so it's empty for the next test

        // Delete all entities in a batch for more efficiency
        droneRepository.deleteAllInBatch();
        stationRepository.deleteAllInBatch();
        pizzaRepository.deleteAllInBatch();
        deliveryRepository.deleteAllInBatch();

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
    @DirtiesContext
    void testFindAll() {
        List<Drone> drones = droneRepository.findAll();
        System.out.println("######### DRONES: " + drones);
        assertNotNull(drones);
        assertEquals(3, drones.size());
    }

    @Test
    @DirtiesContext
    void testFindDroneWithFewestDeliveries() {
        Drone drone = droneRepository.findDroneWithFewestDeliveries();
        System.out.println("THE DRONE: " + drone.toString());
        assertNotNull(drone);
        assertEquals(3L, drone.getId()); // drone one and two, is attached to a delivery, so we expected drone 3.
    }


}