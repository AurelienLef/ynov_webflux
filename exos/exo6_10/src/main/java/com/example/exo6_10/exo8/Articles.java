package com.example.exo6_10.exo8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Articles {
    private String id;
    private String name;
    private BigDecimal price;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", nom = '" + name + '\'' +
                ", prix = " + price +
                '}';
    }
}