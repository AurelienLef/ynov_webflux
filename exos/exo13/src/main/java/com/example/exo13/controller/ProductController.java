package com.example.exo13.controller;

import com.example.exo13.models.Product;
import com.example.exo13.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/search")
    public Flux<Product> getProductByName(String name) {
        return productService.findByName(name);
    }

    @PostMapping
    public Mono<Product> createProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Mono<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(product, id);
    }

    @DeleteMapping
    public Mono<Product> deleteProduct(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PutMapping("/{id}/buy")
    public Mono<Product> buyProduct(@PathVariable Long id, @RequestBody Integer quantity) {
        return productService.buy(id, quantity);
    }
}
