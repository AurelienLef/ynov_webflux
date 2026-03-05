package com.example.tp1.service;

import com.example.tp1.models.Order;
import com.example.tp1.models.OrderRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OrderService {
    public Mono<Order> processOrder(OrderRequest orderRequest) {
        if (orderRequest == null || orderRequest.getCustomerId() == null) {
            return Mono.error(new IllegalArgumentException("Invalid order request"));
        }

        Flux<String> products = Flux.fromIterable(orderRequest.getProductIds())
                .filter(productId -> productId != null)
                .take(100);
    }
}
