package ru.java.mentor.indexator;

import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class StoreOriginalWebClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<Integer> saveOriginalText(String text) {
        log.info("save to db text: " + text);

        return webClientBuilder
                .build()
                .post()
//                .uri(new URI("http://storeoriginaldata/text"))
                .uri("http://localhost:8083/text")
//                .accept(MediaType.TEXT_EVENT_STREAM)
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just(text), String.class)
                .retrieve()
                .bodyToMono(Integer.class);
    }
}
