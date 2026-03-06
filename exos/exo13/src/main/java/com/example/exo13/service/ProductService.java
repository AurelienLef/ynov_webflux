package com.example.exo13.service;

import com.example.exo13.models.Product;
import com.example.exo13.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    public Mono<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Flux<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public Mono<Product> create(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> update(Product product, Long id) {
        return productRepository.findById(id)
                .flatMap(elem -> {
                    elem.setName(product.getName());
                    elem.setPrice(product.getPrice());
                    elem.setStock(product.getStock());
                    return productRepository.save(elem);
                });
    }

    public Mono<Product> delete(Long id) {
        return productRepository.findById(id);
    }

    public Mono<Product> buy(Long id, Integer quantity) {
        if (productRepository.findById(id) == null) {
            return Mono.error(new IllegalArgumentException("Product introuvable"));
        }

        return productRepository.findById(id)
                .flatMap(elem -> {
                    if (elem.getStock() < quantity) {
                        return Mono.error(new IllegalArgumentException("Pas assez de stock"));
                    }
                    elem.setStock(elem.getStock() - quantity);
                    return productRepository.save(elem);
                });
    }
}
