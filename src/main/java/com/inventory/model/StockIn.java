package com.inventory.model;

import java.time.LocalDateTime;

public class StockIn {

    private Long id;
    private Long productId;
    private Integer quantity;
    private String note;
    private LocalDateTime createdAt;

    public StockIn() {
    }

    public StockIn(
            Long productId,
            Integer quantity,
            String note) {

        this.productId = productId;
        this.quantity = quantity;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}