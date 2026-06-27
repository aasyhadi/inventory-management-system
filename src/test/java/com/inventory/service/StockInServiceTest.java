package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.interfaces.IProductRepository;
import com.inventory.repository.interfaces.IStockInRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class StockInServiceTest {

    private IStockInRepository stockInRepository;
    private IProductRepository productRepository;

    private StockInService stockInService;

    @BeforeEach
    void setup() {
        stockInRepository = mock(IStockInRepository.class);
        productRepository = mock(IProductRepository.class);

        stockInService =
                new StockInService(
                        stockInRepository,
                        productRepository
                );
    }

    @Test
    void stockIn_success() {
        Product product =
                new Product(
                        "SKU001",
                        "Mouse",
                        1L,
                        1L,
                        new BigDecimal("100000"),
                        10,
                        2
                );

        product.setId(1L);

        when(productRepository.findById(1L))
                .thenReturn(product);

        stockInService.stockIn(
                1L,
                5,
                "Purchase"
        );

        assertEquals(15, product.getStock());

        verify(productRepository)
                .update(eq(1L), any(Product.class));

        verify(stockInRepository)
                .save(any());
    }

    @Test
    void stockIn_productNotFound() {
        when(productRepository.findById(99L))
                .thenReturn(null);

        assertThrows(
                RuntimeException.class,
                () -> stockInService.stockIn(
                        99L,
                        5,
                        "Purchase"
                )
        );

        verify(productRepository, never())
                .update(anyLong(), any(Product.class));

        verify(stockInRepository, never())
                .save(any());
    }

    @Test
    void stockIn_invalidQuantity() {
        assertThrows(
                RuntimeException.class,
                () -> stockInService.stockIn(
                        1L,
                        0,
                        "Purchase"
                )
        );

        verify(productRepository, never())
                .update(anyLong(), any(Product.class));

        verify(stockInRepository, never())
                .save(any());
    }
}