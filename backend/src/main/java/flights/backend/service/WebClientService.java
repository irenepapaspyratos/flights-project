package flights.backend.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class WebClientService {
    WebClient webClient = WebClient.create();

    public List<List<List<String>>> getOneIataPage(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<List<List<String>>>>() {
                })
                .block()
                .getBody();
    }

}
