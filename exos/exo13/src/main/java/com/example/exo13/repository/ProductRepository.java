package com.example.exo13.repository;

import com.example.exo13.models.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findByName(String name);
}
