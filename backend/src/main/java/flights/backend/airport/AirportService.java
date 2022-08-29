package flights.backend.airport;

import flights.backend.exception.IataNotFoundException;
import flights.backend.exception.IcaoNotFoundException;
import flights.backend.exception.IdNotFoundException;
import flights.backend.service.UniqueIdService;
import flights.backend.service.WebClientService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AirportService {
    private final UniqueIdService uniqueIdService;
    private final AirportRepo airportRepo;
    private final WebClientService webClientService;

    public AirportService(AirportRepo airportRepo, UniqueIdService uniqueIdService, WebClientService webClientService) {
        this.airportRepo = airportRepo;
        this.uniqueIdService = uniqueIdService;
        this.webClientService = webClientService;
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

        for (char alpha = 'A'; alpha <= 'Z'; alpha++) {
            try {
                List<List<List<String>>> singlePage = webClientService.getOneIataPage(baseUrl + "_" + alpha);
                assert singlePage != null;
                List<AirportWithoutId> resultList = parse(singlePage);
                finalResultList =
                        !finalResultList.isEmpty() ?
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
                uniqueIdService.buildUUID(),
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
        assert !airportApiList.isEmpty();
        List<Airport> airportsToSave = new ArrayList<>();

        for (AirportWithoutId airportWithoutId : airportApiList) {
            Airport airport =
                    new Airport(
                            uniqueIdService.buildUUID(),
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

    public List<Airport> upsertAllAirports() {
        List<AirportWithoutId> airportApiList = requestAllAirports();
        assert !airportApiList.isEmpty();
        List<Airport> airportsToSave = new ArrayList<>();

        for (AirportWithoutId airportWithoutId : airportApiList) {
            Airport airportToUpdate =
                    new Airport(
                            airportRepo.findAirportByIata(airportWithoutId.iata()) != null ?
                                    airportRepo.findAirportByIata(airportWithoutId.iata()).id() : uniqueIdService.buildUUID(),
                            airportWithoutId.iata(),
                            airportWithoutId.icao(),
                            airportWithoutId.airportName(),
                            airportWithoutId.locationServed(),
                            airportWithoutId.time(),
                            airportWithoutId.dst()
                    );
            airportsToSave.add(airportToUpdate);
        }

        return airportRepo.saveAll(airportsToSave);
    }

    public Airport getAirportById(String id) {
        return airportRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    public Airport getAirportByIata(String iata) {
        if (airportRepo.findAirportByIata(iata) != null)
            return airportRepo.findAirportByIata(iata);
        throw new IataNotFoundException(iata);
    }

    public Airport getAirportByIcao(String icao) {
        if (airportRepo.findAirportByIcao(icao) != null)
            return airportRepo.findAirportByIcao(icao);
        throw new IcaoNotFoundException(icao);
    }

    public List<Airport> getAllAirports() {
        return airportRepo.findAll();
    }
}
