package com.example.springcloudcircuitbreakerreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    private final WebClient webClient;
    private final ReactiveCircuitBreaker readingListCircuitBreaker;

    //CONSTRUCTOR, already has predefined values set to it
    public MovieService(ReactiveCircuitBreakerFactory circuitBreakerFactory) {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8090").build();
        this.readingListCircuitBreaker = circuitBreakerFactory.create("movieinfo");
    }

    public Mono<String> watchList() {
        return readingListCircuitBreaker.run(webClient.get().uri("/movieinfo").retrieve().bodyToMono(String.class), throwable -> {
            LOG.warn("Error making request to movie info service", throwable);
            return Mono.just("Movies");
        });
    }
}