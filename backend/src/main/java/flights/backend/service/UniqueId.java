package flights.backend.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UniqueId {
    public String buildUUID() {
        return UUID.randomUUID().toString();
    }
}
