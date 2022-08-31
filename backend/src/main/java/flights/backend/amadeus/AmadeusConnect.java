package flights.backend.amadeus;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;

enum AmadeusConnect {
    INSTANCE;
    private final Amadeus amadeus;


    AmadeusConnect() {
        this.amadeus = Amadeus
                .builder(System.getenv("AMADEUS_API_KEY"), System.getenv("AMADEUS_API_SECRET"))
                .build();
    }

    public Location[] location(String keyword) throws ResponseException {
        return amadeus.referenceData.locations.get(Params
                .with("keyword", keyword)
                .and("subType", Locations.AIRPORT));
    }
}