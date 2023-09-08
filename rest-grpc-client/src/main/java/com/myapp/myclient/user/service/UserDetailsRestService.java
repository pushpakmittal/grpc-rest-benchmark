package com.myapp.myclient.user.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserDetailsRestService {

    private final AtomicInteger ID_GENERATOR = new AtomicInteger();
    private WebClient webClient;

    @PostConstruct
    private void init() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8081").build();
    }

    public Flux<Object> generateUsers(Integer range) {
        return Flux.range(0, range)
                .flatMap(i -> getUser());
    }

    private Mono<Object> getUser() {
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToMono(Object.class);
    }
}