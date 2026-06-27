package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.model.StockOut;
import com.inventory.repository.interfaces.IProductRepository;
import com.inventory.repository.interfaces.IStockOutRepository;

import java.util.List;

public class StockOutService {

    private final IStockOutRepository stockOutRepository;
    private final IProductRepository productRepository;

    public StockOutService(
            IStockOutRepository stockOutRepository,
            IProductRepository productRepository) {

        this.stockOutRepository = stockOutRepository;
        this.productRepository = productRepository;
    }

    public void stockOut(
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

        if (product.getStock() < quantity) {
            throw new RuntimeException("Stock is not enough");
        }

        product.setStock(
                product.getStock() - quantity
        );

        productRepository.update(
                productId,
                product
        );

        StockOut stockOut =
                new StockOut(
                        productId,
                        quantity,
                        note
                );

        stockOutRepository.save(stockOut);
    }

    public List<StockOut> getHistory() {
        return stockOutRepository.findAll();
    }
}