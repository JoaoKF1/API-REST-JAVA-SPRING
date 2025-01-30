package com.example.APIrest_JavaSpring.domain.product;

public record ResponseProduct (
        String id,
        String name,
        Integer price_in_cents,
        boolean active

){
    public ResponseProduct(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getPrice_in_cents(),
                product.getActive()
        );
    }
}
