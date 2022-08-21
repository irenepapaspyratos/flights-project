package flights.backend.airport;

import org.springframework.data.annotation.Id;

public record Airport(
        @Id String id,
        String iata,
        String icao,
        String airportName,
        String locationServed,
        String time,
        String dst
) {
}