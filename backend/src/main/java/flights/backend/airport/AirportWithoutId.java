package flights.backend.airport;

public record AirportWithoutId(
        String iata,
        String icao,
        String airportName,
        String locationServed,
        String time,
        String dst
) {
}
