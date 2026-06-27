package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.interfaces.IProductRepository;
import com.inventory.repository.interfaces.IStockOutRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class StockOutServiceTest {

    private IStockOutRepository stockOutRepository;
    private IProductRepository productRepository;

    private StockOutService stockOutService;

    @BeforeEach
    void setup() {
        stockOutRepository = mock(IStockOutRepository.class);
        productRepository = mock(IProductRepository.class);

        stockOutService =
                new StockOutService(
                        stockOutRepository,
                        productRepository
                );
    }

    @Test
    void stockOut_success() {
        Product product =
                new Product(
                        "SKU001",
                        "Mouse",
                        1L,
                        1L,
                        new BigDecimal("100000"),
                        20,
                        2
                );

        product.setId(1L);

        when(productRepository.findById(1L))
                .thenReturn(product);

        stockOutService.stockOut(
                1L,
                5,
                "Sale"
        );

        assertEquals(15, product.getStock());

        verify(productRepository)
                .update(eq(1L), any(Product.class));

        verify(stockOutRepository)
                .save(any());
    }

    @Test
    void stockOut_insufficientStock() {
        Product product =
                new Product(
                        "SKU001",
                        "Mouse",
                        1L,
                        1L,
                        new BigDecimal("100000"),
                        3,
                        2
                );

        when(productRepository.findById(1L))
                .thenReturn(product);

        assertThrows(
                RuntimeException.class,
                () -> stockOutService.stockOut(
                        1L,
                        10,
                        "Sale"
                )
        );

        verify(productRepository, never())
                .update(anyLong(), any(Product.class));

        verify(stockOutRepository, never())
                .save(any());
    }

    @Test
    void stockOut_productNotFound() {
        when(productRepository.findById(99L))
                .thenReturn(null);

        assertThrows(
                RuntimeException.class,
                () -> stockOutService.stockOut(
                        99L,
                        5,
                        "Sale"
                )
        );

        verify(productRepository, never())
                .update(anyLong(), any(Product.class));

        verify(stockOutRepository, never())
                .save(any());
    }

    @Test
    void stockOut_invalidQuantity() {
        assertThrows(
                RuntimeException.class,
                () -> stockOutService.stockOut(
                        1L,
                        0,
                        "Sale"
                )
        );

        verify(productRepository, never())
                .update(anyLong(), any(Product.class));

        verify(stockOutRepository, never())
                .save(any());
    }
}