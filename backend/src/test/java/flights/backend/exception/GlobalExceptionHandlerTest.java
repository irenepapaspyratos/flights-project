package flights.backend.exception;

import flights.backend.airport.AirportRepo;
import flights.backend.airport.AirportService;
import flights.backend.service.UniqueIdService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
class GlobalExceptionHandlerTest {

    private final AirportRepo airportRepoMock = mock(AirportRepo.class);
    public UniqueIdService uniqueIdService = new UniqueIdService();
    public AirportService airportService = new AirportService(airportRepoMock, uniqueIdService);

    @Test
    void getAirportById_nonExisting() {
        when(airportRepoMock.findById(any(String.class))).thenThrow(new IdNotFoundException("NonExistingId"));

        assertThatExceptionOfType(IdNotFoundException.class)
                .isThrownBy(() -> airportService.getAirportById("NonExistingId"));

    }

}