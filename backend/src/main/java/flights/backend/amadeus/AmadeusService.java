package flights.backend.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import org.springframework.stereotype.Service;

@Service
public class AmadeusService {

    private final AmadeusConnect amadeusConnect;

    AmadeusService(AmadeusConnect amadeusConnect) {
        this.amadeusConnect = amadeusConnect;
    }

    public Location[] getAmadeusLocations(String keyword) throws ResponseException {
        return amadeusConnect.location(keyword);
    }


    public FlightOfferSearch[] getFlights(String origin,
                                          String destination,
                                          String departDate,
                                          String adults,
                                          String returnDate,
                                          Integer max) throws ResponseException {
        return amadeusConnect.flights(origin, destination, departDate, adults, returnDate, max);
    }
}
