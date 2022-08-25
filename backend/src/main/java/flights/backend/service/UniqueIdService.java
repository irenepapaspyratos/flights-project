package flights.backend.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UniqueIdService {
    public String buildUUID() {
        return UUID.randomUUID().toString();
    }
}
