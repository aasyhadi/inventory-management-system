package com.inventory.menu;

import com.inventory.model.Product;
import com.inventory.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductMenu {

    private final ProductService service;
    private final Scanner scanner;

    public ProductMenu(
            ProductService service,
            Scanner scanner) {

        this.service = service;
        this.scanner = scanner;
    }

    public void show() {
        int choice;

        do {
            System.out.println("\n=== PRODUCT MENU ===");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Low Stock Products");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> viewProducts();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> viewLowStockProducts();
                case 0 -> System.out.println("Back...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void addProduct() {
        try {
            System.out.print("SKU: ");
            String sku = scanner.nextLine();

            System.out.print("Product Name: ");
            String name = scanner.nextLine();

            System.out.print("Category ID: ");
            Long categoryId = Long.parseLong(scanner.nextLine());

            System.out.print("Supplier ID: ");
            Long supplierId = Long.parseLong(scanner.nextLine());

            System.out.print("Price: ");
            BigDecimal price = new BigDecimal(scanner.nextLine());

            System.out.print("Stock: ");
            Integer stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Minimum Stock: ");
            Integer minimumStock = Integer.parseInt(scanner.nextLine());

            Product product = new Product(
                    sku,
                    name,
                    categoryId,
                    supplierId,
                    price,
                    stock,
                    minimumStock
            );

            service.addProduct(product);

            System.out.println("Product added successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewProducts() {
        ArrayList<Product> products =
                service.getAllProducts();

        System.out.println(
                "\nID | SKU | NAME | CAT_ID | SUP_ID | PRICE | STOCK | MIN"
        );

        for (Product product : products) {
            System.out.println(
                    product.getId()
                            + " | "
                            + product.getSku()
                            + " | "
                            + product.getName()
                            + " | "
                            + product.getCategoryId()
                            + " | "
                            + product.getSupplierId()
                            + " | "
                            + product.getPrice()
                            + " | "
                            + product.getStock()
                            + " | "
                            + product.getMinimumStock()
            );
        }
    }

    private void updateProduct() {
        try {
            System.out.print("Product ID: ");
            Long id = Long.parseLong(scanner.nextLine());

            System.out.print("SKU: ");
            String sku = scanner.nextLine();

            System.out.print("Product Name: ");
            String name = scanner.nextLine();

            System.out.print("Category ID: ");
            Long categoryId = Long.parseLong(scanner.nextLine());

            System.out.print("Supplier ID: ");
            Long supplierId = Long.parseLong(scanner.nextLine());

            System.out.print("Price: ");
            BigDecimal price = new BigDecimal(scanner.nextLine());

            System.out.print("Stock: ");
            Integer stock = Integer.parseInt(scanner.nextLine());

            System.out.print("Minimum Stock: ");
            Integer minimumStock = Integer.parseInt(scanner.nextLine());

            Product product = new Product(
                    sku,
                    name,
                    categoryId,
                    supplierId,
                    price,
                    stock,
                    minimumStock
            );

            service.updateProduct(id, product);

            System.out.println("Product updated successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteProduct() {
        try {
            System.out.print("Product ID: ");
            Long id = Long.parseLong(scanner.nextLine());

            service.deleteProduct(id);

            System.out.println("Product deleted successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewLowStockProducts() {
        ArrayList<Product> products =
                service.getLowStockProducts();

        System.out.println("\n=== LOW STOCK PRODUCTS ===");
        System.out.println("ID | SKU | NAME | STOCK | MIN");

        for (Product product : products) {
            System.out.println(
                    product.getId()
                            + " | "
                            + product.getSku()
                            + " | "
                            + product.getName()
                            + " | "
                            + product.getStock()
                            + " | "
                            + product.getMinimumStock()
            );
        }
    }
}