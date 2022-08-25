package flights.backend.data;

import flights.backend.airport.Airport;
import flights.backend.airport.AirportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "fetch-rate=* * * * * *")
@AutoConfigureMockMvc
class DataIntegrationTest {

    @MockBean
    private AirportService airportService;

    @Autowired
    MockMvc mockMvc;

    public final List<Airport> airportList = List.of(
            new Airport("d7bc902b-8691-4ce6-aee3-c9faaef0c2d0", "AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", ""),
            new Airport("a0e72b0f-c5d5-430c-8da7-b1bed4db77f0", "BDA", "TXKF", "L.F. Wade International Airport", "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00", "Mar-Nov")
    );


    @Test
    void getAllAirports() throws Exception {
        mockMvc.perform(get("/api/data/airports"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(content().json("""
                        []
                        """));
    }

    @DirtiesContext
    @Test
    void addAllAirports() throws Exception {
        Mockito.when(airportService.addAllAirports()).thenReturn(airportList);

        mockMvc.perform(post(
                        "/api/data/airports")
                )
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        [
                        {
                        "id": "d7bc902b-8691-4ce6-aee3-c9faaef0c2d0",
                        "iata": "AAA",
                        "icao": "NTGA",
                        "airportName": "Anaa Airport",
                        "locationServed": "Anaa, Tuamotus, French Polynesia",
                        "time": "UTC−10:00",
                        "dst": ""},
                        {
                        "id": "a0e72b0f-c5d5-430c-8da7-b1bed4db77f0",
                        "iata": "BDA",
                        "icao": "TXKF",
                        "airportName": "L.F. Wade International Airport",
                        "locationServed": "Hamilton, British Overseas Territory of Bermuda",
                        "time": "UTC−04:00",
                        "dst": "Mar-Nov"
                        }
                        ]
                        """));
    }

    @DirtiesContext
    @Test
    void upsertAllAirports() throws Exception {
        Mockito.when(airportService.upsertAllAirports()).thenReturn(airportList);

        mockMvc.perform(put(
                        "/api/data/airports")
                )
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        [
                        {
                        "id": "d7bc902b-8691-4ce6-aee3-c9faaef0c2d0",
                        "iata": "AAA",
                        "icao": "NTGA",
                        "airportName": "Anaa Airport",
                        "locationServed": "Anaa, Tuamotus, French Polynesia",
                        "time": "UTC−10:00",
                        "dst": ""},
                        {
                        "id": "a0e72b0f-c5d5-430c-8da7-b1bed4db77f0",
                        "iata": "BDA",
                        "icao": "TXKF",
                        "airportName": "L.F. Wade International Airport",
                        "locationServed": "Hamilton, British Overseas Territory of Bermuda",
                        "time": "UTC−04:00",
                        "dst": "Mar-Nov"
                        }
                        ]
                        """));
    }
}