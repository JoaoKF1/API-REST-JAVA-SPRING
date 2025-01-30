package com.example.APIrest_JavaSpring.service;

import com.example.APIrest_JavaSpring.domain.product.Product;
import com.example.APIrest_JavaSpring.domain.product.RequestProduct;
import com.example.APIrest_JavaSpring.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllActiveProducts() {
        return repository.findAllByActiveTrue();
    }

    public Product findProductById(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto com ID " + id + " não encontrado"));

    }

    @Transactional
    public Product registerProduct(RequestProduct data) {
        if(data.name() == null || data.name().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser nulo");
        }
        if (data.price_in_cents() <= 0 || data.price_in_cents() == null) {
            throw new IllegalArgumentException("Insira um valor válido");
        }
        Product newProduct = new Product(data);
        return repository.save(newProduct);
    }

    @Transactional
    public Product updateProduct(String id, RequestProduct data) {
        Product updatedProduct = findProductById(id);
        updatedProduct.setName(data.name());
        updatedProduct.setPrice_in_cents(data.price_in_cents());
        repository.save(updatedProduct);
        return updatedProduct;
    }

    @Transactional
    public ResponseEntity<Void> deleteProduct(String id) {
        Product product = findProductById(id);
        product.setActive(false);
        repository.save(product);
        return ResponseEntity.noContent().build();
    }
}
