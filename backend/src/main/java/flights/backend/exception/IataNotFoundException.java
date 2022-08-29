package flights.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IataNotFoundException extends RuntimeException {
    public IataNotFoundException(String iata) {
        super("Iata " + iata + " does not exist!", null, false, false);
    }
}
