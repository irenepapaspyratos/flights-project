package flights.backend.airport;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AirportServiceTest {

    private final AirportRepo airportRepoMock = mock(AirportRepo.class);
    private final AirportService airportService = new AirportService(airportRepoMock);
    public final Airport airport = new Airport("d7bc902b-8691-4ce6-aee3-c9faaef0c2d0",
            "AAA", "NTGA", "Anaa Airport",
            "Anaa, Tuamotus, French Polynesia", "UTC−10:00", "");
    public final List<Airport> airportList = List.of(
            new Airport("d7bc902b-8691-4ce6-aee3-c9faaef0c2d0",
                    "AAA", "NTGA", "Anaa Airport",
                    "Anaa, Tuamotus, French Polynesia", "UTC−10:00", ""),
            new Airport("a0e72b0f-c5d5-430c-8da7-b1bed4db77f0",
                    "BDA", "TXKF", "L.F. Wade International Airport",
                    "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00",
                    "Mar-Nov")
    );

    @Test
    void addAirport() {
        when(airportRepoMock.save(any(Airport.class))).thenReturn(airport);

        Airport actualResult = airportService.addAirport(airport);
        assertThat(actualResult).isEqualTo(airport);
    }

    @Test
    void getAllAirports() {
        when(airportRepoMock.findAll()).thenReturn(airportList);

        List<Airport> actualResult = airportService.getAllAirports();
        assertThat(actualResult).isEqualTo(airportList);
    }
}