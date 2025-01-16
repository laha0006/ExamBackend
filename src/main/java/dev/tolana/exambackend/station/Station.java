package dev.tolana.exambackend.station;


import dev.tolana.exambackend.drone.Drone;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "station")
    private List<Drone> drones;
}
