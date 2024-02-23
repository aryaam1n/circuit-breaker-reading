package com.example.springcloudcircuitbreakerreading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class SpringCloudCircuitBreakerReadingApplication {

//	@RequestMapping("/to-read")
//	public Mono<String> toRead() {
//		return WebClient.builder().build()
//				.get().uri("http://localhost:8090/recommended").retrieve()
//				.bodyToMono(String.class);
//	}

	@Autowired
	private BookService bookService;

	@RequestMapping("/to-read")
	public Mono<String> toRead() {
		return bookService.readingList();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudCircuitBreakerReadingApplication.class, args);
	}

}

//retrieve reading list from the bookstore application
//WEBCLIENT makes a GET request to the bookstore service
