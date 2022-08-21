package flights.backend.service;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class WebClientService {
    WebClient webClient = WebClient.create();

    public List getOneIataPage(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .toEntity(List.class)
                .block()
                .getBody();
    }

}
