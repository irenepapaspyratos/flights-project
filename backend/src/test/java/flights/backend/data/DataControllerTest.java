package flights.backend.data;

import flights.backend.airport.Airport;
import flights.backend.airport.AirportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = "fetch-rate=* * * * * *")
@AutoConfigureMockMvc
class DataControllerTest {

    @SpyBean
    private DataController dataController;

    @Autowired
    MockMvc mockMvc;

    public final List<Airport> airportList = List.of(
            new Airport("d7bc902b-8691-4ce6-aee3-c9faaef0c2d0", "AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", ""),
            new Airport("a0e72b0f-c5d5-430c-8da7-b1bed4db77f0", "BDA", "TXKF", "L.F. Wade International Airport", "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00", "Mar-Nov")
    );


    @Test
    void scheduleIsTriggered() {
        Mockito.doNothing().when(dataController).goUpdate();

        await()
                .atMost(Duration.of(1500, ChronoUnit.MILLIS))
                .untilAsserted(() -> verify(dataController, atLeast(1)).goUpdate());
    }

    @DirtiesContext
    @Test
    void goUpdate_emptyDB() {
        AirportService airportServiceMock = mock(AirportService.class);
        DataController dataControllerMock = new DataController(airportServiceMock);

        when(airportServiceMock.getAllAirports()).thenReturn(ResponseEntity.status(204).body(Collections.emptyList()));
        dataControllerMock.goUpdate();

        verify(airportServiceMock, atLeast(1)).addAllAirports();
    }

    @DirtiesContext
    @Test
    void goUpdate_nonEmptyDB() {
        AirportService airportServiceMock2 = mock(AirportService.class);
        DataController dataControllerMock2 = new DataController(airportServiceMock2);

        when(airportServiceMock2.getAllAirports()).thenReturn(ResponseEntity.status(200).body(airportList));
        dataControllerMock2.goUpdate();

        verify(airportServiceMock2, atLeast(1)).upsertAllAirports();
    }
}
