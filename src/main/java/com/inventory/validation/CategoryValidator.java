package com.inventory.validation;

import com.inventory.model.Category;

public class CategoryValidator {

    public String validateForCreate(Category category) {
        if (category == null) {
            return "Kategori tidak boleh kosong.";
        }

        if (category.getName() == null ||
                category.getName().trim().isEmpty()) {
            return "Nama kategori tidak boleh kosong.";
        }

        return null;
    }

    public String validateForUpdate(Category category) {
        return validateForCreate(category);
    }
}