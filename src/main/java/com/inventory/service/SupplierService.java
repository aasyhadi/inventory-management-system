package com.inventory.service;

import com.inventory.exception.SupplierException;
import com.inventory.model.Supplier;
import com.inventory.repository.interfaces.ISupplierRepository;
import com.inventory.validation.SupplierValidator;

import java.util.ArrayList;

public class SupplierService {

    private final ISupplierRepository repository;
    private final SupplierValidator validator;

    public SupplierService(
            ISupplierRepository repository,
            SupplierValidator validator) {

        this.repository = repository;
        this.validator = validator;
    }

    public void addSupplier(Supplier supplier) {

        String validationMessage =
                validator.validateForCreate(supplier);

        if (validationMessage != null) {
            throw new SupplierException(validationMessage);
        }

        if (repository.findByName(supplier.getName()) != null) {
            throw new SupplierException(
                    "Supplier sudah terdaftar."
            );
        }

        repository.save(supplier);
    }

    public ArrayList<Supplier> getAllSuppliers() {
        return repository.findAll();
    }

    public Supplier getSupplierById(Long id) {

        Supplier supplier =
                repository.findById(id);

        if (supplier == null) {
            throw new SupplierException(
                    "Supplier tidak ditemukan."
            );
        }

        return supplier;
    }

    public void updateSupplier(
            Long id,
            Supplier supplier) {

        String validationMessage =
                validator.validateForUpdate(supplier);

        if (validationMessage != null) {
            throw new SupplierException(validationMessage);
        }

        if (!repository.update(id, supplier)) {
            throw new SupplierException(
                    "Supplier tidak ditemukan."
            );
        }
    }

    public void deleteSupplier(Long id) {

        if (!repository.delete(id)) {
            throw new SupplierException(
                    "Supplier tidak ditemukan."
            );
        }
    }
}