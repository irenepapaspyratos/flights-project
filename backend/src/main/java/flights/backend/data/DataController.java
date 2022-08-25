package flights.backend.data;

import flights.backend.airport.Airport;
import flights.backend.airport.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/data/")
public class DataController {

    private final AirportService airportService;

    public DataController(AirportService airportService) {
        this.airportService = airportService;
    }

    @Scheduled(cron = "${fetch-rate:0 0 0 1 * ?}")
    public void goUpdate() {
        if (airportService.getAllAirports().equals(ResponseEntity.status(204).body(Collections.emptyList()))) {
            airportService.addAllAirports();
        } else {
            airportService.upsertAllAirports();
        }
    }

    @GetMapping("/airports")
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports().getBody();
    }

    @PostMapping("/airports")
    public ResponseEntity<List<Airport>> addAllAirports() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(airportService.addAllAirports());
    }

    @PutMapping("/airports")
    public ResponseEntity<List<Airport>> upsertAllAirports() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(airportService.upsertAllAirports());
    }
}
