package dev.tolana.exambackend.drone;

import dev.tolana.exambackend.drone.dto.DroneDto;
import dev.tolana.exambackend.drone.dto.DroneToDtoMapper;
import dev.tolana.exambackend.station.Station;
import dev.tolana.exambackend.station.StationService;
import dev.tolana.exambackend.station.dto.StationDtoWithoutDrones;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@Transactional
class DroneServiceImplTest {
    @Mock
    DroneRepository droneRepository;

    @Mock
    DroneToDtoMapper droneToDtoMapper;

    @InjectMocks
    DroneServiceImpl droneServiceImpl;


    @Test
    void testGetAllDrones() {
        // Arrange
        Station stationOne = Station.builder()
                .latitude(55.43)
                .longitude(12.35)
                .build();

        Drone droneOne = Drone.builder()
                .id(1L)
                .uuid("u-one")
                .status(OperationStatus.IN_SERVICE)
                .station(stationOne)
                .build();

        Drone droneTwo = Drone.builder()
                .id(2L)
                .uuid("u-two")
                .status(OperationStatus.IN_SERVICE)
                .station(stationOne)
                .build();

        Drone droneThree = Drone.builder()
                .id(3L)
                .uuid("u-three")
                .status(OperationStatus.IN_SERVICE)
                .station(stationOne)
                .build();

        DroneDto droneDtoOne = new DroneDto(1L,
                "u-one",
                OperationStatus.IN_SERVICE,
                new StationDtoWithoutDrones(
                        1,
                        55.43,
                        12.35
                ));

        DroneDto droneDtoTwo = new DroneDto(2L,
                "u-two",
                OperationStatus.IN_SERVICE,
                new StationDtoWithoutDrones(
                        1,
                        55.43,
                        12.35
                ));

        DroneDto droneDtoThree = new DroneDto(3L,
                "u-three",
                OperationStatus.IN_SERVICE,
                new StationDtoWithoutDrones(
                        1,
                        55.43,
                        12.35
                ));

        List<Drone> droneList = new ArrayList<>(List.of(droneOne, droneTwo, droneThree));
        List<DroneDto> expectedDtos = new ArrayList<>(List.of(droneDtoOne, droneDtoTwo, droneDtoThree));

        when(droneRepository.findAll()).thenReturn(droneList);
        when(droneToDtoMapper.apply(any(Drone.class)))
                .thenAnswer(invocation -> {
                    Drone drone = invocation.getArgument(0);
                    return new DroneDto(drone.getId(), drone.getUuid(), drone.getStatus(),
                            new StationDtoWithoutDrones(
                                    1,
                                    55.43,
                                    12.35));
                });


        // Act
        List<DroneDto> actualDtos = droneServiceImpl.getAllDrones();


        // Assert
        assertEquals(actualDtos, expectedDtos);

    }

}