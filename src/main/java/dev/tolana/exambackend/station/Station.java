package dev.tolana.exambackend.station;


import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.tolana.exambackend.drone.Drone;
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
