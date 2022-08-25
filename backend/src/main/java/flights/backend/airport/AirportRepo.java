package flights.backend.airport;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepo extends MongoRepository<Airport, String> {
    Airport findAirportByIata(String iata);
}
