package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.model.StockIn;
import com.inventory.repository.interfaces.IProductRepository;
import com.inventory.repository.interfaces.IStockInRepository;

import java.util.List;

public class StockInService {

    private final IStockInRepository stockInRepository;
    private final IProductRepository productRepository;

    public StockInService(
            IStockInRepository stockInRepository,
            IProductRepository productRepository) {

        this.stockInRepository = stockInRepository;
        this.productRepository = productRepository;
    }

    public void stockIn(
            Long productId,
            Integer quantity,
            String note) {

        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        Product product =
                productRepository.findById(productId);

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        product.setStock(product.getStock() + quantity);

        productRepository.update(productId, product);

        StockIn stockIn =
                new StockIn(
                        productId,
                        quantity,
                        note
                );

        stockInRepository.save(stockIn);
    }

    public List<StockIn> getHistory() {
        return stockInRepository.findAll();
    }
}