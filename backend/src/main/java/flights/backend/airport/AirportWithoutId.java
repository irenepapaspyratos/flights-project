package flights.backend.airport;

public record AirportWithoutId(
        String iata,
        String icao,
        String name,
        String locationServed,
        String time,
        String dst
) {
}
