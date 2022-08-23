package flights.backend.data;

import flights.backend.airport.AirportService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataController {

    public DataController(AirportService airportService) {
        this.airportService = airportService;
    }

    private final AirportService airportService;

    @Scheduled(cron = "0 39 * * * ?")
    public void goUpdate() {
        airportService.addAllAirports(airportService.requestAllAirports());
    }
}
