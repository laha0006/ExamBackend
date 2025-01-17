package dev.tolana.exambackend.drone.model;

import dev.tolana.exambackend.station.Station;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String uuid;
    private OperationStatus status;

    @ManyToOne
//    @JsonManagedReference
    private Station station;



}
