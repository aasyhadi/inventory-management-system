package com.inventory.service;

import com.inventory.exception.ProductException;
import com.inventory.model.Product;
import com.inventory.repository.interfaces.ICategoryRepository;
import com.inventory.repository.interfaces.IProductRepository;
import com.inventory.repository.interfaces.ISupplierRepository;
import com.inventory.validation.ProductValidator;

import java.util.ArrayList;

public class ProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final ISupplierRepository supplierRepository;
    private final ProductValidator validator;

    public ProductService(
            IProductRepository productRepository,
            ICategoryRepository categoryRepository,
            ISupplierRepository supplierRepository,
            ProductValidator validator) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.validator = validator;
    }

    public void addProduct(Product product) {
        String validationMessage =
                validator.validateForCreate(product);

        if (validationMessage != null) {
            throw new ProductException(validationMessage);
        }

        if (productRepository.findBySku(product.getSku()) != null) {
            throw new ProductException("SKU produk sudah terdaftar.");
        }

        if (categoryRepository.findById(product.getCategoryId()) == null) {
            throw new ProductException("Kategori tidak ditemukan.");
        }

        if (supplierRepository.findById(product.getSupplierId()) == null) {
            throw new ProductException("Supplier tidak ditemukan.");
        }

        productRepository.save(product);
    }

    public ArrayList<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new ProductException("Produk tidak ditemukan.");
        }

        return product;
    }

    public void updateProduct(
            Long id,
            Product product) {

        String validationMessage =
                validator.validateForUpdate(product);

        if (validationMessage != null) {
            throw new ProductException(validationMessage);
        }

        if (categoryRepository.findById(product.getCategoryId()) == null) {
            throw new ProductException("Kategori tidak ditemukan.");
        }

        if (supplierRepository.findById(product.getSupplierId()) == null) {
            throw new ProductException("Supplier tidak ditemukan.");
        }

        if (!productRepository.update(id, product)) {
            throw new ProductException("Produk tidak ditemukan.");
        }
    }

    public void deleteProduct(Long id) {
        if (!productRepository.delete(id)) {
            throw new ProductException("Produk tidak ditemukan.");
        }
    }

    public ArrayList<Product> getLowStockProducts() {
        ArrayList<Product> products =
                productRepository.findAll();

        ArrayList<Product> lowStockProducts =
                new ArrayList<>();

        for (Product product : products) {
            if (product.getStock() <= product.getMinimumStock()) {
                lowStockProducts.add(product);
            }
        }

        return lowStockProducts;
    }
}