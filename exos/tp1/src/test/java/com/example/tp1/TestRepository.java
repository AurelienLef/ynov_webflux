package com.example.tp1;

import com.example.tp1.repository.ProductRepository;
import java.util.List;

public class TestRepository {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();

        System.out.println("Test findById");
        System.out.println(productRepository.findById("2").block());

        System.out.println("\nTest findbyIds");
        productRepository.findByIds(List.of("1", "3", "5")).doOnNext(System.out::println).blockLast();

        System.out.println("\nTest getStock");
        System.out.println("Stock : " + productRepository.getStock("2").block());
    }
}
