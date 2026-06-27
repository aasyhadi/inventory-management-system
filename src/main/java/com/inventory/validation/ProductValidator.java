package com.inventory.validation;

import com.inventory.model.Product;

public class ProductValidator {

    public String validateForCreate(Product product) {

        if (product == null) {
            return "Produk tidak boleh kosong.";
        }

        if (product.getSku() == null ||
                product.getSku().trim().isEmpty()) {
            return "SKU tidak boleh kosong.";
        }

        if (product.getName() == null ||
                product.getName().trim().isEmpty()) {
            return "Nama produk tidak boleh kosong.";
        }

        return null;
    }

    public String validateForUpdate(Product product) {
        return validateForCreate(product);
    }
}