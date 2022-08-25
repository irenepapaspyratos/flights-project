package flights.backend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class WebClientServiceTest {

    @Autowired
    MockMvc mockMvc;

    @DirtiesContext
    @Test
    void getOneIataPage() throws Exception {
        mockMvc.perform(get("https://www.wikitable2json.com/api/List_of_airports_by_IATA_airport_code:_A"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(content().json("""
                        []
                        """));
    }

}