package dev.tolana.exambackend.station;


import dev.tolana.exambackend.drone.model.Drone;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double latitude;   // north & south
    private double longitude;  // east & west

    @OneToMany(mappedBy = "station")
//    @JsonBackReference
    private List<Drone> drones;
}
