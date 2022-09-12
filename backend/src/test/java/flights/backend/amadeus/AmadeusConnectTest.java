package flights.backend.amadeus;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AmadeusConnectTest {

    WireMockServer wireMockServer;

    private Amadeus amadeus;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(1234);
        wireMockServer.start();

        String address = "/v1/security/oauth2/token"
                + "?grant_type=client_credentials&client_secret=DEMO&client_id=DEMO";
        wireMockServer.stubFor(post(urlEqualTo(address))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("auth_ok.json")));

        amadeus = Amadeus
                .builder("DEMO", "DEMO")
                .setHost("localhost")
                .setPort(1234)
                .setSsl(false)
                .setLogLevel("debug")
                .build();
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    @Test
    void location() throws ResponseException {
        String address = "/v1/reference-data/locations";

        wireMockServer.stubFor(
                get(urlPathEqualTo(address))
                        .withQueryParam("keyword", matching("Lon"))
                        .withQueryParam("subType", matching("AIRPORT"))
                        .willReturn(aResponse().withHeader("Content-Type", "application/json")
                                .withStatus(200)
                                .withBodyFile("locationRef_ok.json")
                        ));

        Location[] result = amadeus.referenceData.locations.get(Params
                .with("keyword", "Lon")
                .and("subType", Locations.AIRPORT)
        );

        then(result).isNotNull();
    }

    @Test
    void flights() throws ResponseException {
        String address = "/v2/shopping/flight-offers";

        wireMockServer.stubFor(
                get(urlPathEqualTo(address))
                        .withQueryParam("originLocationCode", matching("LON"))
                        .withQueryParam("destinationLocationCode", matching("NYC"))
                        .withQueryParam("departureDate", matching("2022-11-15"))
                        .withQueryParam("returnDate", matching("2022-11-17"))
                        .withQueryParam("adults", matching("1"))
                        .withQueryParam("max", matching("1"))
                        .willReturn(aResponse().withHeader("Content-Type", "application/json")
                                .withStatus(200)
                                .withBodyFile("flightOffer_ok.json")
                        ));

        FlightOfferSearch[] result = amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", "LON")
                        .and("destinationLocationCode", "NYC")
                        .and("departureDate", "2022-11-15")
                        .and("returnDate", "2022-11-17")
                        .and("adults", "1")
                        .and("max", "1")
        );

        then(result).isNotNull();
    }

    @Test
    void AmadeusBuilder_ok() {
        Amadeus.builder("id", "secret");
        assertTrue(true,
                "should return a Configuration");
    }

    @Test
    void AmadeusBuilder_NoId() {
        assertThrows(NullPointerException.class, () -> Amadeus.builder(null, "secret").build());
    }

    @Test
    void AmadeusBuilder_NoSecret() {
        assertThrows(NullPointerException.class, () -> Amadeus.builder("id", null).build());
    }
}
