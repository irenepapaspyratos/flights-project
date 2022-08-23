package flights.backend.airport;

import flights.backend.exception.IdNotFoundException;
import flights.backend.service.UniqueId;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AirportServiceTest {

    private final UniqueId uniqueIdMock = mock(UniqueId.class);
    private final AirportRepo airportRepoMock = mock(AirportRepo.class);

    public AirportService airportServiceMock = mock(AirportService.class);

    private final UniqueId uniqueId = new UniqueId();
    private final AirportService airportService = new AirportService(airportRepoMock, uniqueId);
    public final AirportWithoutId airportFromApi1 = new AirportWithoutId(
            "AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", "");
    public final Airport airport1 = new Airport(
            "d7bc902b-8691-4ce6-aee3-c9faaef0c2d0", "AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", "");
    public final List<AirportWithoutId> airportsFromApiList = List.of(
            new AirportWithoutId("AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", ""),
            new AirportWithoutId("BDA", "TXKF", "L.F. Wade International Airport", "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00", "Mar-Nov"));
    public final List<Airport> airportList = List.of(
            new Airport("d7bc902b-8691-4ce6-aee3-c9faaef0c2d0", "AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", ""),
            new Airport("a0e72b0f-c5d5-430c-8da7-b1bed4db77f0", "BDA", "TXKF", "L.F. Wade International Airport", "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00", "Mar-Nov")
    );


    @Test
    void parse_empty() {
        //given
        List<List<List<String>>> listOfApi = Collections.emptyList();
        //when
        List<AirportWithoutId> actual = AirportService.parse(listOfApi);
        //then
        assertThat(actual).isEmpty();
    }

    @Test
    void parse_one() {
        List<List<List<String>>> listOfApi = Collections.singletonList(Collections.singletonList(List.of("AAA", "NTGA", "Anaa Airport",
                "Anaa, Tuamotus, French Polynesia", "UTC−10:00", "")));

        List<AirportWithoutId> actual = AirportService.parse(listOfApi);

        assertThat(actual).containsExactly(
                new AirportWithoutId("AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", ""));
    }

    @Test
    void parse_list() {
        List<List<List<String>>> listOfApi = Collections.singletonList(List.of(
                List.of("AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", ""),
                List.of("BDA", "TXKF", "L.F. Wade International Airport", "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00", "Mar-Nov")
        ));

        List<AirportWithoutId> actual = AirportService.parse(listOfApi);

        assertThat(actual).containsExactly(
                new AirportWithoutId("AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", ""),
                new AirportWithoutId("BDA", "TXKF", "L.F. Wade International Airport", "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00", "Mar-Nov")
        );
    }

    @Test
    void parse_listWithFilteredTableHead() {
        List<List<List<String>>> listOfApi = Collections.singletonList(List.of(
                List.of("IATA", "ICAO", "Airport name", "Location served", "Time", "DST"),
                List.of("BDA", "TXKF", "L.F. Wade International Airport", "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00", "Mar-Nov")
        ));

        List<AirportWithoutId> actual = AirportService.parse(listOfApi);

        assertThat(actual).containsExactly(
                new AirportWithoutId("BDA", "TXKF", "L.F. Wade International Airport", "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00", "Mar-Nov")
        );
    }

    @Test
    void parse_listWithFilteredSeparator() {
        List<List<List<String>>> listOfApi = Collections.singletonList(List.of(
                List.of("-CA-", "-CA-", "-CA-", "-CA-", "-CA-", "-CA-"),
                List.of("BDA", "TXKF", "L.F. Wade International Airport", "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00", "Mar-Nov")
        ));

        List<AirportWithoutId> actual = AirportService.parse(listOfApi);

        assertThat(actual).containsExactly(
                new AirportWithoutId("BDA", "TXKF", "L.F. Wade International Airport", "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00", "Mar-Nov")
        );
    }

    @Test
    void addAirport() {
        when(airportRepoMock.save(any(Airport.class))).thenReturn(airport1);

        Airport actualResult = airportService.addAirport(airportFromApi1);
        assertThat(actualResult).isEqualTo(airport1);
    }

    @Test
    void addAllAirports() throws NoSuchFieldException, IllegalAccessException {
        when(airportServiceMock.addAllAirports()).thenCallRealMethod();
        when(airportServiceMock.requestAllAirports()).thenReturn(airportsFromApiList);
        when(uniqueIdMock.buildUUID())
                .thenReturn("d7bc902b-8691-4ce6-aee3-c9faaef0c2d0")
                .thenReturn("a0e72b0f-c5d5-430c-8da7-b1bed4db77f0");

        when(airportRepoMock.saveAll(airportList)).thenReturn(airportList);

        Field repoField = AirportService.class.getDeclaredField("airportRepo");
        Field idField = AirportService.class.getDeclaredField("uniqueId");
        repoField.setAccessible(true);
        idField.setAccessible(true);
        repoField.set(airportServiceMock, airportRepoMock);
        idField.set(airportServiceMock, uniqueIdMock);
        airportServiceMock.addAllAirports();

        verify(airportRepoMock, times(1)).saveAll(airportList);
    }

    @Test
    void getAirportById_existing() {
        when(airportRepoMock.findById(any(String.class))).thenReturn(Optional.of(airport1));

        Airport actualResult = airportService.getAirportById("d7bc902b-8691-4ce6-aee3-c9faaef0c2d0");
        assertThat(actualResult).isEqualTo(
                new Airport("d7bc902b-8691-4ce6-aee3-c9faaef0c2d0", "AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", "")
        );
    }

    @Test
    void getAirportById_nonExisting() {
        when(airportRepoMock.findById(any(String.class))).thenThrow(new IdNotFoundException("NonExistingId"));

        assertThatExceptionOfType(IdNotFoundException.class)
                .isThrownBy(() -> airportService.getAirportById("NonExistingId"));
    }

    @Test
    void getAllAirports() {
        when(airportRepoMock.findAll()).thenReturn(airportList);

        List<Airport> actualResult = airportService.getAllAirports();
        assertThat(actualResult).containsExactly(
                new Airport("d7bc902b-8691-4ce6-aee3-c9faaef0c2d0", "AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", ""),
                new Airport("a0e72b0f-c5d5-430c-8da7-b1bed4db77f0", "BDA", "TXKF", "L.F. Wade International Airport", "Hamilton, British Overseas Territory of Bermuda", "UTC−04:00", "Mar-Nov")
        );
    }
}