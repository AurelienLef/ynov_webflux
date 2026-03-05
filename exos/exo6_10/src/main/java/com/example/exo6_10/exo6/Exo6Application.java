package com.example.exo6_10.exo6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
@RestController
public class Exo6Application {

	@GetMapping("/api/processed-numbers")
	public List<String> getProcessedNumbers() {
		return IntStream.rangeClosed(1, 10)
				.filter(n -> n % 2 == 0)
				.map(n -> n * 10)
				.mapToObj(n -> "Processed " + n)
				.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		SpringApplication.run(Exo6Application.class, args);
	}
}
