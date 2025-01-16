package dev.tolana.exambackend.drone;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.tolana.exambackend.station.Station;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

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
