package flights.backend.service;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class WebClientServiceTest {

    @Autowired
    MockMvc mockMvc;

    private final MockWebServer mockWebServer = new MockWebServer();

    private final WebClientService webClient = new WebClientService();

    @AfterEach
    public void shutDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DirtiesContext
    @Test
    void getOneIataPage() {

        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .setBody("""
                                [[["AAA", "NTGA", "Anaa Airport", "Anaa, Tuamotus, French Polynesia", "UTC−10:00", ""]]]"""));

        List<List<List<String>>> response = webClient.getOneIataPage(mockWebServer.url("localhost/").toString());
        assertThat(response).hasToString("""
                [[[AAA, NTGA, Anaa Airport, Anaa, Tuamotus, French Polynesia, UTC−10:00, ]]]""");
    }

    @DirtiesContext
    @Test
    void getOneIataPage_null() {

        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .setBody(""));

        List<List<List<String>>> response = webClient.getOneIataPage(mockWebServer.url("localhost/").toString());
        assertThat(response).isNull();
    }

}
