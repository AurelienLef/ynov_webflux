package com.example.exo1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class Exo1Application {

	public static void main(String[] args) {
		Flux<Integer> flux = Flux.range(1, 10).map(x -> x*3).filter(x -> x>15);
		flux.subscribe(System.out::println);
	}

}
