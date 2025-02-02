package com.example.APIrest_JavaSpring.service;

import com.example.APIrest_JavaSpring.domain.product.Product;
import com.example.APIrest_JavaSpring.domain.product.RequestProduct;
import com.example.APIrest_JavaSpring.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnProductByIdWhenExists() {
        String id = "1";
        var product = new Product(id, "Teste", 2000, true);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        var result = productService.findProductById(id);

        assertNotNull(result);
        verify(productRepository).findById(id);
        assertEquals("Teste", result.getName());
    }
    @Test
    void shouldReturnProductByIdWhenNotExists() {
        String id = "1";
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> productService.findProductById(id));

        assertEquals("Produto com ID " + id + " não encontrado", exception.getMessage());
        verify(productRepository).findById(id);
    }

    @Test
    void shouldRegisterProductSucessfully() {
        String id = "1";
        var requestProduct = new RequestProduct("Produto Teste", 2000, true);
        var product = new Product(requestProduct);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        var result = productService.registerProduct(requestProduct);

        assertNotNull(result);
        assertEquals(product.getId(), result.getId());
        assertEquals("Produto Teste", product.getName());
        assertEquals(2000, product.getPrice_in_cents());
        assertEquals(true, product.getActive());
        verify(productRepository).save(product);
    }

    @Test
    void shouldRegisterProductNotSucessfully() {
        var product = new RequestProduct("Teste", 0, false);

        Exception exception = assertThrows(RuntimeException.class, () -> productService.registerProduct(product));

        String expectedMessage1 = "O nome do produto não pode ser nulo";
        String expectedMessage2 = "Insira um valor válido";
        assertTrue(exception.getMessage().contains(expectedMessage1) || exception.getMessage().contains(expectedMessage2));
    }

    @Test
    void shouldUpdateProductSucessfully() {
        String id = "1";
        var requestProduct = new RequestProduct("newTest", 2000, true);
        Product updatedProduct = new Product(requestProduct);
        var product = new Product(id, "oldTest", 3000, true);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        var result = productService.updateProduct(id, requestProduct);

        assertNotNull(result);
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getPrice_in_cents(), result.getPrice_in_cents());
        verify(productRepository).save(any(Product.class));
        verify(productRepository).findById(id);
    }

    @Test
    void shouldUpdateProductNotSucessfully() {
        String id = "1";
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> productService.deleteProduct(id));

        assertEquals("Produto com ID " + id + " não encontrado", exception.getMessage());
        verify(productRepository).findById(id);
    }

    @Test
    void deleteProductSucessfully() {
        String id = "1";
        var product = new Product(id, "Test", 2000, true);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        var result = productService.deleteProduct(id);

        assertNotNull(result);
        assertFalse(product.getActive());
        verify(productRepository).save(product);
        verify(productRepository).findById(id);
    }

    @Test
    void deleteProductNotSucessfully() {
        String id = "1";
        var product = new Product(id, "Test", 2000, true);
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> productService.deleteProduct(id));

        assertEquals("Produto com ID " + id + " não encontrado", exception.getMessage());
        verify(productRepository).findById(id);

    }
}