package dev.tolana.exambackend.config;

import dev.tolana.exambackend.delivery.model.Delivery;
import dev.tolana.exambackend.delivery.DeliveryRepository;
import dev.tolana.exambackend.drone.model.Drone;
import dev.tolana.exambackend.drone.DroneRepository;
import dev.tolana.exambackend.drone.model.OperationStatus;
import dev.tolana.exambackend.pizza.Pizza;
import dev.tolana.exambackend.pizza.PizzaRepository;
import dev.tolana.exambackend.station.Station;
import dev.tolana.exambackend.station.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final DroneRepository droneRepository;
    private final StationRepository stationRepository;
    private final PizzaRepository pizzaRepository;
    private final DeliveryRepository deliveryRepository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("#### INIT DATA ####");
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

        Pizza pizzaFive = Pizza.builder()
                .price(78)
                .title("Kebab Pizza")
                .build();


        pizzaRepository.save(pizzaOne);
        pizzaRepository.save(pizzaTwo);
        pizzaRepository.save(pizzaThree);
        pizzaRepository.save(pizzaFour);
        pizzaRepository.save(pizzaFive);

        Delivery delivery = Delivery.builder()
                .pizza(pizzaOne)
                .address("Home")
                .estimatedDeliveryTime(LocalDateTime.now().plusMinutes(30))
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
}
