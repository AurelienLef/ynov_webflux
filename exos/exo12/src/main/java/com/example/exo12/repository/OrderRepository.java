package com.example.exo12.repository;

import com.example.exo12.models.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    Flux<Order> findAllByStatus(String etat);

    Flux<Order> findAllPaged(Integer page, Integer size);
}
