package com.inventory.repository.interfaces;

import com.inventory.model.Supplier;

import java.util.ArrayList;

public interface ISupplierRepository {

    void save(Supplier supplier);

    ArrayList<Supplier> findAll();

    Supplier findById(Long id);

    Supplier findByName(String name);

    boolean update(Long id, Supplier supplier);

    boolean delete(Long id);

    long count();
}