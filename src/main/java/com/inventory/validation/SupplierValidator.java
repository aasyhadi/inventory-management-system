package com.inventory.validation;

import com.inventory.model.Supplier;

public class SupplierValidator {

    public String validateForCreate(Supplier supplier) {

        if (supplier == null) {
            return "Supplier tidak boleh kosong.";
        }

        if (supplier.getName() == null ||
                supplier.getName().trim().isEmpty()) {
            return "Nama supplier tidak boleh kosong.";
        }

        return null;
    }

    public String validateForUpdate(Supplier supplier) {
        return validateForCreate(supplier);
    }
}