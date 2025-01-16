package dev.tolana.exambackend.station;

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

    @GetMapping("/stations")
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }
}
