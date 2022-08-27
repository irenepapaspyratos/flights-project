package flights.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleIdNotFoundException(IdNotFoundException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("message_key", "ID_NOT_FOUND");

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IataNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleIataNotFoundException(IataNotFoundException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("message_key", "IATA_NOT_FOUND");

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IcaoNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleIcaoNotFoundException(IcaoNotFoundException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("message_key", "ICAO_NOT_FOUND");

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}
