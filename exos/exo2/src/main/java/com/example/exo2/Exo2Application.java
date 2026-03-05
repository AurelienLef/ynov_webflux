package com.example.exo2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class Exo2Application {

	public static void main(String[] args) {
		AtomicInteger callCount = new AtomicInteger(0);
		
		Mono.fromCallable(() -> {
			int count = callCount.incrementAndGet();
			if (count <= 2) {
				throw new RuntimeException("Tentative " + count + " : Erreur");
			}
			return "Tentative " + count + " : Réussite";
		})
		.doOnError(error -> System.out.println(error.getMessage()))
		.retry(2)
		.subscribe(
			value -> System.out.println(value),
			error -> System.out.println(error.getMessage())
		);
	}
}
