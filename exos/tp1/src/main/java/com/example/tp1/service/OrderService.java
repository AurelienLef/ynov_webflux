package com.example.tp1.service;

import com.example.tp1.models.Order;
import com.example.tp1.models.OrderRequest;
import com.example.tp1.models.Product;
import com.example.tp1.repository.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OrderService {
    public Mono<Order> processOrder(OrderRequest orderRequest) {
        ProductRepository productRepository = new ProductRepository();

        if (orderRequest == null || orderRequest.getCustomerId() == null) {
            return Mono.error(new IllegalArgumentException("Invalid order request"));
        }

        Flux<String> productsIds = Flux.fromIterable(orderRequest.getProductIds())
                .filter(productId -> productId != null)
                .take(100);

        Flux<Product> products = productsIds.flatMap(id -> productRepository.findById(id)
                .onErrorResume(e -> {
                    System.out.println("Produit ignoré " + id);
                    return Mono.empty();
                })
        ).filter(product -> product.getStock() > 0);
    }
}
