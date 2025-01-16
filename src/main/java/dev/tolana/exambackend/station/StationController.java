package dev.tolana.exambackend.station;

import dev.tolana.exambackend.station.dto.StationDto;
import dev.tolana.exambackend.station.dto.StationToDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;
    private final StationToDtoMapper stationToDtoMapper;

    @GetMapping("/stations")
    public List<StationDto> getAllStations() {
        return stationService.getAllStations().stream().map(stationToDtoMapper).toList();
    }
}
