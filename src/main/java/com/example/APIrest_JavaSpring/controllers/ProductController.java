package com.example.APIrest_JavaSpring.controllers;

import com.example.APIrest_JavaSpring.domain.product.Product;
import com.example.APIrest_JavaSpring.dtos.ProductResponseDTO;
import com.example.APIrest_JavaSpring.repositories.ProductRepository;
import com.example.APIrest_JavaSpring.domain.product.RequestProduct;
import com.example.APIrest_JavaSpring.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductService productService;
    @Autowired
    private DataSource dataSource;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllActiveProducts();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable @RequestBody @Valid String id) {
        productService.findProductById(id);
        return ResponseEntity.ok().body(productService.findProductById(id));

    }

    @PostMapping
    public ResponseEntity<Void> registerProduct(@RequestBody @Valid RequestProduct data) {
        productService.registerProduct(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String id, @RequestBody @Valid RequestProduct data) {
        Product updatedProduct = productService.updateProduct(id, data);
        return ResponseEntity.ok(new ProductResponseDTO(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
