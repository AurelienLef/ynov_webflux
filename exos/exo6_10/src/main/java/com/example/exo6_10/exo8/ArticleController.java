package com.example.exo6_10.exo8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class ArticleController {
    private final Map<String, Articles> articles = new HashMap<>();

    public ArticleController() {
        // 5 produits en dur
        articles.put("1", new Articles("1", "Intro to Spring", new BigDecimal("1000")));
        articles.put("2", new Articles("2", "Reactive programming", new BigDecimal("600")));
        articles.put("3", new Articles("3", "Building api", new BigDecimal("400")));
    }

    @GetMapping("/api/articles")
    public Flux<String> getArticles() {
        return Flux.fromIterable(articles.values()).map(Articles::toString);
    }

    public static void main(String[] args) {
        SpringApplication.run(ArticleController.class, args);
    }
}
