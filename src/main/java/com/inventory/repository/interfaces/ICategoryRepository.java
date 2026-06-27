package com.inventory.repository.interfaces;

import com.inventory.model.Category;

import java.util.ArrayList;

public interface ICategoryRepository {

    void save(Category category);

    ArrayList<Category> findAll();

    Category findById(Long id);

    Category findByName(String name);

    boolean update(Long id, Category category);

    boolean delete(Long id);

    long count();
}