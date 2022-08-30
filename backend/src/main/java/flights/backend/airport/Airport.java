package flights.backend.airport;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("airports")
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
