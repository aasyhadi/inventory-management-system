package com.inventory.repository.interfaces;

import com.inventory.model.Product;

import java.util.ArrayList;

public interface IProductRepository {

    void save(Product product);

    ArrayList<Product> findAll();

    Product findById(Long id);

    Product findBySku(String sku);

    boolean update(Long id, Product product);

    boolean delete(Long id);

    long count();
}