package flights.backend.airport;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Document(collection = "airports")
public interface AirportRepo extends MongoRepository<Airport, String> {
    Airport findAirportByIata(String iata);

    Airport findAirportByIcao(String icao);
}
