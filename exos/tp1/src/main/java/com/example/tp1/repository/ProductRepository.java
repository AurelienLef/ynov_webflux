package com.example.tp1.repository;

import com.example.tp1.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<String, Product> products = new HashMap<>();

    public ProductRepository() {
        // 5 produits en dur
        products.put("1", new Product("1", "Ordi", new BigDecimal("1000"), 1, "Multimédia"));
        products.put("2", new Product("2", "Écran", new BigDecimal("600"), 2, "Multimédia"));
        products.put("3", new Product("3", "Pomme", new BigDecimal("400"), 3, "Alimentaire"));
        products.put("4", new Product("4", "Lit", new BigDecimal("150"), 4, "Literie"));
        products.put("5", new Product("5", "Piano", new BigDecimal("50"), 5, "Musique"));
    }

    public Mono<Product> findById(String id) {
        return Mono.justOrEmpty(products.get(id)).delayElement(Duration.ofMillis(100));
    }

    public Flux<Product> findByIds(List<String> ids) {
        return Mono.just(ids).flatMapIterable(list -> list).flatMap(this::findById).delayElements(Duration.ofMillis(100));
    }

    public Mono<Integer> getStock(String productId) {
        return Mono.justOrEmpty(products.get(productId)).map(Product::getStock).delayElement(Duration.ofMillis(100));
    }
}
