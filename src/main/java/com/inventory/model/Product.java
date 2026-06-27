package com.inventory.model;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String sku;
    private String name;

    private Long categoryId;
    private Long supplierId;

    private BigDecimal price;

    private Integer stock;
    private Integer minimumStock;

    public Product() {
    }

    public Product(
            String sku,
            String name,
            Long categoryId,
            Long supplierId,
            BigDecimal price,
            Integer stock,
            Integer minimumStock) {

        this.sku = sku;
        this.name = name;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.price = price;
        this.stock = stock;
        this.minimumStock = minimumStock;
    }

    public Product(
            Long id,
            String sku,
            String name,
            Long categoryId,
            Long supplierId,
            BigDecimal price,
            Integer stock,
            Integer minimumStock) {

        this.id = id;
        this.sku = sku;
        this.name = name;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.price = price;
        this.stock = stock;
        this.minimumStock = minimumStock;
    }

    public Long getId() { return id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public Long getCategoryId() { return categoryId; }
    public Long getSupplierId() { return supplierId; }
    public BigDecimal getPrice() { return price; }
    public Integer getStock() { return stock; }
    public Integer getMinimumStock() { return minimumStock; }

    public void setId(Long id) { this.id = id; }
    public void setSku(String sku) { this.sku = sku; }
    public void setName(String name) { this.name = name; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setStock(Integer stock) { this.stock = stock; }
    public void setMinimumStock(Integer minimumStock) { this.minimumStock = minimumStock; }
}