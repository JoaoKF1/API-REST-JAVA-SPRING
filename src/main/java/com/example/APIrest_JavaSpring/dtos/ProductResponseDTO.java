package com.example.APIrest_JavaSpring.dtos;

import com.example.APIrest_JavaSpring.domain.product.Product;

public record ProductResponseDTO(
        String id,
        String name,
        Integer price_in_cents,
        boolean active

){
    public ProductResponseDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getPrice_in_cents(),
                product.getActive()
        );
    }
}
