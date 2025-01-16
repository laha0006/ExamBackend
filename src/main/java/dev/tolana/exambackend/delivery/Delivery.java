package dev.tolana.exambackend.delivery;


import dev.tolana.exambackend.drone.Drone;
import dev.tolana.exambackend.pizza.Pizza;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String address;
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime actualDeliveryTime;

    @ManyToOne
    private Pizza pizza;

    @ManyToOne
    private Drone drone;
}
