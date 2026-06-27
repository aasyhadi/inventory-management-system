package com.inventory.service;

import com.inventory.exception.CategoryException;
import com.inventory.model.Category;
import com.inventory.repository.interfaces.ICategoryRepository;
import com.inventory.validation.CategoryValidator;

import java.util.ArrayList;

public class CategoryService {

    private final ICategoryRepository repository;
    private final CategoryValidator validator;

    public CategoryService(
            ICategoryRepository repository,
            CategoryValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public void addCategory(Category category) {
        String validationMessage =
                validator.validateForCreate(category);

        if (validationMessage != null) {
            throw new CategoryException(validationMessage);
        }

        if (repository.findByName(category.getName()) != null) {
            throw new CategoryException(
                    "Nama kategori sudah terdaftar."
            );
        }

        repository.save(category);
    }

    public ArrayList<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category getCategoryById(Long id) {
        Category category = repository.findById(id);

        if (category == null) {
            throw new CategoryException(
                    "Kategori tidak ditemukan."
            );
        }

        return category;
    }

    public void updateCategory(Long id, Category category) {
        String validationMessage =
                validator.validateForUpdate(category);

        if (validationMessage != null) {
            throw new CategoryException(validationMessage);
        }

        if (!repository.update(id, category)) {
            throw new CategoryException(
                    "Kategori tidak ditemukan."
            );
        }
    }

    public void deleteCategory(Long id) {
        if (!repository.delete(id)) {
            throw new CategoryException(
                    "Kategori tidak ditemukan."
            );
        }
    }
}