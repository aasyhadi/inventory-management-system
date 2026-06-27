package com.inventory.model;

import java.time.LocalDateTime;

public class StockOut {

    private Long id;
    private Long productId;
    private Integer quantity;
    private String note;
    private LocalDateTime createdAt;

    public StockOut() {
    }

    public StockOut(
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

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getNote() {
        return note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}