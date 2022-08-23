package flights.backend.airport;

import flights.backend.exception.IdNotFoundException;
import flights.backend.service.WebClientService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AirportService {
    private final AirportRepo airportRepo;

    public AirportService(AirportRepo airportRepo) {
        this.airportRepo = airportRepo;
    }

    public String buildUUID() {
        return UUID.randomUUID().toString();
    }

    public static List<AirportWithoutId> parse(List<List<List<String>>> listApi) {
        return listApi.stream().flatMap(
                resultList -> resultList.stream()
                        .filter(result -> !result.get(0).equals("IATA") && !result.get(0).equals("ICAO"))
                        .filter(result -> !result.get(0).equals(result.get(1)) && !result.get(0).equals(result.get(2)))
                        .map(result ->
                                new AirportWithoutId(
                                        result.get(0),
                                        result.get(1),
                                        result.get(2),
                                        result.get(3),
                                        result.get(4),
                                        result.get(5)
                                ))
        ).distinct().collect(Collectors.toList());
    }

    public List<AirportWithoutId> requestAllAirports() {
        String baseUrl = "https://www.wikitable2json.com/api/List_of_airports_by_IATA_airport_code:";
        List<AirportWithoutId> finalResultList = new ArrayList<>();
        WebClientService call = new WebClientService();

        for (char alpha = 'A'; alpha <= 'Z'; alpha++) {
            try {
                List<List<List<String>>> singlePage = call.getOneIataPage(baseUrl + "_" + alpha);
                System.out.println(alpha);
                List<AirportWithoutId> resultList = parse(singlePage);

                finalResultList = !finalResultList.isEmpty() ?
                        Stream.concat(finalResultList.stream(), resultList.stream()).distinct().toList()
                        : resultList;
            } catch (Exception e) {
                System.out.println("ERROR: " + alpha);
            }
        }

        return finalResultList;
    }

    public Airport addAirport(@NonNull AirportWithoutId airportFromApi) {
        Airport airport = new Airport(
                buildUUID(),
                airportFromApi.iata(),
                airportFromApi.icao(),
                airportFromApi.airportName(),
                airportFromApi.locationServed(),
                airportFromApi.time(),
                airportFromApi.dst()
        );
        return airportRepo.save(airport);
    }

    public List<Airport> updateAllAirports(@NonNull List<AirportWithoutId> airportApiList) {
        List<Airport> airportsToReturn = new ArrayList<>();
        int listSize = airportApiList.size();

        // First of List defines the former table header
        for (int i = 1; i < listSize; i++) {
            airportsToReturn.add(addAirport(airportApiList.get(i)));
        }
        return airportsToReturn;
    }

    public Airport getAirportById(String id) {
        return airportRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    public List<Airport> getAllAirports() {
        return airportRepo.findAll();
    }
}
