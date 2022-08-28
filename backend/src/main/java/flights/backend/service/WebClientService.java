package flights.backend.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Service
public class WebClientService {
    WebClient webClient = WebClient.create();

    public List<List<List<String>>> getOneIataPage(String url) {
        ResponseEntity<List<List<List<String>>>> response = webClient.get()
                .uri(url)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<List<List<String>>>>() {
                })
                .block();

        if (response == null)
            return Collections.emptyList();
        return response.getBody();
    }

}
