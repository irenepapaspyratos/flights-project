package flights.backend.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/amadeus")
public class AmadeusController {

    private final AmadeusService amadeusService;

    public AmadeusController(AmadeusService amadeusService) {
        this.amadeusService = amadeusService;
    }

    @GetMapping("/locations")
    public Location[] locations(@RequestParam(required = true) String keyword) throws ResponseException {
        return amadeusService.getAmadeusLocations(keyword);
    }

    @GetMapping("/flights")
    public FlightOfferSearch[] flights(@RequestParam(required = true) String origin,
                                       @RequestParam(required = true) String destination,
                                       @RequestParam(required = true) String departDate,
                                       @RequestParam(required = true) String adults,
                                       @RequestParam(required = false) String returnDate,
                                       @RequestParam(required = false) Integer max)
            throws ResponseException {
        return amadeusService.getFlights(origin, destination, departDate, adults, returnDate, max);
    }
}
