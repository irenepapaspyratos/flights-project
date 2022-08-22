package flights.backend.airport;

import flights.backend.service.WebClientService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class AirportService {
    private final AirportRepo airportRepo;

    public AirportService(AirportRepo airportRepo) {
        this.airportRepo = airportRepo;
    }

    public List<List<String>> requestAllAirportsWithTableHeader() {
        String baseUrl = "https://www.wikitable2json.com/api/List_of_airports_by_IATA_airport_code:";
        List<List<String>> finalResultList = new ArrayList<>();
        WebClientService call = new WebClientService();

        for (char alpha = 'A'; alpha <= 'Z'; alpha++) {
            try {
                List<List<List<String>>> singlePage = call.getOneIataPage(baseUrl + "_" + alpha);
                System.out.println(alpha);
                List<List<String>> resultList = singlePage.get(0);
                List<List<String>> cleanResultList = resultList.stream()
                        .filter(r -> !r.get(0).equals(r.get(1)) && !r.get(0).equals(r.get(2)))
                        .toList();

                finalResultList = !finalResultList.isEmpty() ?
                        Stream.concat(finalResultList.stream(), cleanResultList.stream()).distinct().toList()
                        : cleanResultList;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return finalResultList;
    }

    public Airport buildAirport(@NonNull List<String> attributesList) {
        return new Airport(
                UUID.randomUUID().toString(),
                attributesList.get(0),
                attributesList.get(1),
                attributesList.get(2),
                attributesList.get(3),
                attributesList.get(4),
                attributesList.get(5)
        );
    }

    public Airport addAirport(Airport airport) {
        return airportRepo.save(airport);
    }

    public List<Airport> updateAllAirports() {
        List<List<String>> airports = requestAllAirportsWithTableHeader();
        int listSize = airports.size();

        List<Airport> airportsToReturn = new ArrayList<>();
        for (int i = 1; i < listSize; i++) {
            Airport airportToInsert = buildAirport(airports.get(i));
            addAirport(airportToInsert);
            airportsToReturn.add(airportToInsert);
        }
        return airportsToReturn;
    }

    public List<Airport> getAllAirports() {
        return airportRepo.findAll();
    }
}
