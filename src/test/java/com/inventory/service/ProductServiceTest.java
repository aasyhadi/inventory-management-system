package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.interfaces.ICategoryRepository;
import com.inventory.repository.interfaces.IProductRepository;
import com.inventory.repository.interfaces.ISupplierRepository;
import com.inventory.validation.ProductValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private IProductRepository productRepository;
    private ICategoryRepository categoryRepository;
    private ISupplierRepository supplierRepository;

    private ProductService productService;

    @BeforeEach
    void setup() {

        productRepository =
                mock(IProductRepository.class);

        categoryRepository =
                mock(ICategoryRepository.class);

        supplierRepository =
                mock(ISupplierRepository.class);

        productService =
                new ProductService(
                        productRepository,
                        categoryRepository,
                        supplierRepository,
                        new ProductValidator()
                );
    }

    @Test
    void addProduct_success() {

        Product product =
                new Product(
                        "SKU001",
                        "Laptop",
                        1L,
                        1L,
                        new BigDecimal("10000000"),
                        10,
                        2
                );

        when(productRepository.findBySku("SKU001"))
                .thenReturn(null);

        when(categoryRepository.findById(1L))
                .thenReturn(new com.inventory.model.Category(1L, "Electronics"));

        when(supplierRepository.findById(1L))
                .thenReturn(new com.inventory.model.Supplier(
                        1L,
                        "ABC Supplier",
                        "08123456789",
                        "Jakarta"
                ));

        productService.addProduct(product);

        verify(productRepository)
                .save(product);
    }

    @Test
    void addProduct_duplicateSku() {

        Product product =
                new Product(
                        "SKU001",
                        "Laptop",
                        1L,
                        1L,
                        new BigDecimal("10000000"),
                        10,
                        2
                );

        when(productRepository.findBySku("SKU001"))
                .thenReturn(product);

        org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> productService.addProduct(product)
        );

        verify(productRepository, never())
                .save(any(Product.class));
    }

    @Test
    void addProduct_categoryNotFound() {

        Product product =
                new Product(
                        "SKU002",
                        "Keyboard",
                        99L,
                        1L,
                        new BigDecimal("250000"),
                        10,
                        2
                );

        when(productRepository.findBySku("SKU002"))
                .thenReturn(null);

        when(categoryRepository.findById(99L))
                .thenReturn(null);

        org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> productService.addProduct(product)
        );

        verify(productRepository, never())
                .save(any(Product.class));
    }

    @Test
    void addProduct_supplierNotFound() {

        Product product =
                new Product(
                        "SKU003",
                        "Monitor",
                        1L,
                        99L,
                        new BigDecimal("1500000"),
                        5,
                        1
                );

        com.inventory.model.Category category =
                new com.inventory.model.Category();

        category.setId(1L);
        category.setName("Electronics");

        when(productRepository.findBySku("SKU003"))
                .thenReturn(null);

        when(categoryRepository.findById(1L))
                .thenReturn(category);

        when(supplierRepository.findById(99L))
                .thenReturn(null);

        org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> productService.addProduct(product)
        );

        verify(productRepository, never())
                .save(any(Product.class));
    }




}