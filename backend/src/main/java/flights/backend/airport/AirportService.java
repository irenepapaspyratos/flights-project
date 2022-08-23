package flights.backend.airport;

import flights.backend.exception.IdNotFoundException;
import flights.backend.service.UniqueId;
import flights.backend.service.WebClientService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AirportService {
    public final UniqueId uniqueId;
    private final AirportRepo airportRepo;

    public AirportService(AirportRepo airportRepo, UniqueId uniqueId) {
        this.airportRepo = airportRepo;
        this.uniqueId = uniqueId;
    }

    public static List<AirportWithoutId> parse(@NonNull List<List<List<String>>> airportListFromApi) {
        return airportListFromApi.stream().flatMap(
                resultList -> resultList.stream()
                        .filter(result ->
                                !result.isEmpty() && !result.get(0).equals("IATA") && !result.get(0).equals("ICAO")
                        )
                        .filter(result ->
                                !result.get(0).equals(result.get(1)) && !result.get(0).equals(result.get(2))
                        )
                        .map(result ->
                                new AirportWithoutId(
                                        result.get(0),
                                        result.get(1),
                                        result.get(2),
                                        result.size() < 4 ? "" : result.get(3),
                                        result.size() < 5 ? "" : result.get(4),
                                        result.size() < 6 ? "" : result.get(5)
                                ))
        ).distinct().toList();
    }

    public List<AirportWithoutId> requestAllAirports() {
        String baseUrl = "https://www.wikitable2json.com/api/List_of_airports_by_IATA_airport_code:";
        List<AirportWithoutId> finalResultList = new ArrayList<>();
        WebClientService call = new WebClientService();

        for (char alpha = 'A'; alpha <= 'Z'; alpha++) {
            try {
                List<List<List<String>>> singlePage = call.getOneIataPage(baseUrl + "_" + alpha);
                List<AirportWithoutId> resultList = parse(singlePage);
                finalResultList = !finalResultList.isEmpty() ?
                        Stream.concat(finalResultList.stream(), resultList.stream()).distinct().toList()
                        : resultList;
            } catch (Exception e) {
                finalResultList = Collections.emptyList();
            }
        }

        return finalResultList;
    }

    public Airport addAirport(@NonNull AirportWithoutId airportFromApi) {
        Airport airport = new Airport(
                uniqueId.buildUUID(),
                airportFromApi.iata(),
                airportFromApi.icao(),
                airportFromApi.airportName(),
                airportFromApi.locationServed(),
                airportFromApi.time(),
                airportFromApi.dst()
        );
        return airportRepo.save(airport);
    }

    public List<Airport> addAllAirports() {
        List<AirportWithoutId> airportApiList = requestAllAirports();
        List<Airport> airportsToSave = new ArrayList<>();

        for (AirportWithoutId airportWithoutId : airportApiList) {
            Airport airport = new Airport(
                    uniqueId.buildUUID(),
                    airportWithoutId.iata(),
                    airportWithoutId.icao(),
                    airportWithoutId.airportName(),
                    airportWithoutId.locationServed(),
                    airportWithoutId.time(),
                    airportWithoutId.dst()
            );
            airportsToSave.add(airport);
        }
        return airportRepo.saveAll(airportsToSave);
    }

    public Airport getAirportById(String id) {
        return airportRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    public List<Airport> getAllAirports() {
        return airportRepo.findAll();
    }
}
