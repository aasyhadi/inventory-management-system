package com.inventory.menu;

import com.inventory.model.Supplier;
import com.inventory.service.SupplierService;

import java.util.ArrayList;
import java.util.Scanner;

public class SupplierMenu {

    private final SupplierService service;
    private final Scanner scanner;

    public SupplierMenu(
            SupplierService service,
            Scanner scanner) {

        this.service = service;
        this.scanner = scanner;
    }

    public void show() {

        int choice;

        do {

            System.out.println("\n=== SUPPLIER MENU ===");
            System.out.println("1. Add Supplier");
            System.out.println("2. View Suppliers");
            System.out.println("3. Update Supplier");
            System.out.println("4. Delete Supplier");
            System.out.println("0. Back");

            System.out.print("Choose : ");

            choice =
                    Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1 -> addSupplier();
                case 2 -> viewSuppliers();
                case 3 -> updateSupplier();
                case 4 -> deleteSupplier();
                case 0 -> System.out.println("Back...");
                default -> System.out.println("Invalid Menu");
            }

        } while (choice != 0);
    }

    private void addSupplier() {

        try {

            System.out.print("Name : ");
            String name = scanner.nextLine();

            System.out.print("Phone : ");
            String phone = scanner.nextLine();

            System.out.print("Address : ");
            String address = scanner.nextLine();

            service.addSupplier(
                    new Supplier(
                            name,
                            phone,
                            address
                    )
            );

            System.out.println(
                    "Supplier added successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error : " + e.getMessage()
            );
        }
    }

    private void viewSuppliers() {

        ArrayList<Supplier> suppliers =
                service.getAllSuppliers();

        System.out.println(
                "\nID | NAME | PHONE"
        );

        for (Supplier supplier : suppliers) {

            System.out.println(
                    supplier.getId()
                            + " | "
                            + supplier.getName()
                            + " | "
                            + supplier.getPhone()
            );
        }
    }

    private void updateSupplier() {

        try {

            System.out.print("ID : ");
            Long id =
                    Long.parseLong(scanner.nextLine());

            System.out.print("Name : ");
            String name = scanner.nextLine();

            System.out.print("Phone : ");
            String phone = scanner.nextLine();

            System.out.print("Address : ");
            String address = scanner.nextLine();

            service.updateSupplier(
                    id,
                    new Supplier(
                            name,
                            phone,
                            address
                    )
            );

            System.out.println(
                    "Supplier updated successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error : " + e.getMessage()
            );
        }
    }

    private void deleteSupplier() {

        try {

            System.out.print("ID : ");
            Long id =
                    Long.parseLong(scanner.nextLine());

            service.deleteSupplier(id);

            System.out.println(
                    "Supplier deleted successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error : " + e.getMessage()
            );
        }
    }
}