package com.example.springcloudcircuitbreakerreading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class SpringCloudCircuitBreakerCatalogApplication {

//	@RequestMapping("/to-read")
//	public Mono<String> toRead() {
//		return WebClient.builder().build()
//				.get().uri("http://localhost:8090/recommended").retrieve()
//				.bodyToMono(String.class);
//	}

	@Autowired
	private MovieService movieService;

	@RequestMapping("/to-watch")
	public Mono<String> toWatch() {
		return movieService.watchList();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudCircuitBreakerCatalogApplication.class, args);
	}

}

//retrieve reading list from the bookstore application
//WEBCLIENT makes a GET request to the bookstore service
