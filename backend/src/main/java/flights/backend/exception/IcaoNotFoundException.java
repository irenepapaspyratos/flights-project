package flights.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IcaoNotFoundException extends RuntimeException {
    public IcaoNotFoundException(String icao) {
        super("Icao " + icao + " does not exist!", null, false, false);
    }
}
