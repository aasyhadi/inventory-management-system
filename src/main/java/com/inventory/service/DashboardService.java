package com.inventory.service;

import com.inventory.repository.impl.CategoryRepositoryImpl;
import com.inventory.repository.impl.ProductRepositoryImpl;
import com.inventory.repository.impl.SupplierRepositoryImpl;

public class DashboardService {

    private final CategoryRepositoryImpl categoryRepository;
    private final SupplierRepositoryImpl supplierRepository;
    private final ProductRepositoryImpl productRepository;

    public DashboardService(
            CategoryRepositoryImpl categoryRepository,
            SupplierRepositoryImpl supplierRepository,
            ProductRepositoryImpl productRepository) {

        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
    }

    public long totalCategories() {
        return categoryRepository.count();
    }

    public long totalSuppliers() {
        return supplierRepository.count();
    }

    public long totalProducts() {
        return productRepository.count();
    }

    public double inventoryValue() {
        return productRepository.totalInventoryValue();
    }
}