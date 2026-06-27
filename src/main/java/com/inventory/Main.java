package com.inventory;

import com.inventory.menu.CategoryMenu;
import com.inventory.menu.ProductMenu;
import com.inventory.menu.StockInMenu;
import com.inventory.menu.SupplierMenu;

import com.inventory.repository.impl.CategoryRepositoryImpl;
import com.inventory.repository.impl.ProductRepositoryImpl;
import com.inventory.repository.impl.StockInRepositoryImpl;
import com.inventory.repository.impl.SupplierRepositoryImpl;

import com.inventory.service.CategoryService;
import com.inventory.service.ProductService;
import com.inventory.service.StockInService;
import com.inventory.service.SupplierService;

import com.inventory.menu.StockOutMenu;
import com.inventory.repository.impl.StockOutRepositoryImpl;
import com.inventory.service.StockOutService;

import com.inventory.validation.CategoryValidator;
import com.inventory.validation.ProductValidator;
import com.inventory.validation.SupplierValidator;

import com.inventory.menu.DashboardMenu;
import com.inventory.service.DashboardService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CategoryRepositoryImpl categoryRepository =
                new CategoryRepositoryImpl();

        SupplierRepositoryImpl supplierRepository =
                new SupplierRepositoryImpl();

        ProductRepositoryImpl productRepository =
                new ProductRepositoryImpl();

        CategoryService categoryService =
                new CategoryService(
                        categoryRepository,
                        new CategoryValidator()
                );

        SupplierService supplierService =
                new SupplierService(
                        supplierRepository,
                        new SupplierValidator()
                );

        ProductService productService =
                new ProductService(
                        productRepository,
                        categoryRepository,
                        supplierRepository,
                        new ProductValidator()
                );

        StockInService stockInService =
                new StockInService(
                        new StockInRepositoryImpl(),
                        productRepository
                );

        StockOutService stockOutService =
                new StockOutService(
                        new StockOutRepositoryImpl(),
                        productRepository
                );

        StockOutMenu stockOutMenu =
                new StockOutMenu(
                        stockOutService,
                        scanner
                );

        CategoryMenu categoryMenu =
                new CategoryMenu(
                        categoryService,
                        scanner
                );

        SupplierMenu supplierMenu =
                new SupplierMenu(
                        supplierService,
                        scanner
                );

        ProductMenu productMenu =
                new ProductMenu(
                        productService,
                        scanner
                );

        StockInMenu stockInMenu =
                new StockInMenu(
                        stockInService,
                        scanner
                );

        DashboardService dashboardService =
                new DashboardService(
                        categoryRepository,
                        supplierRepository,
                        productRepository
                );

        DashboardMenu dashboardMenu =
                new DashboardMenu(
                        dashboardService
                );

        int choice;

        do {
            System.out.println("\n=== INVENTORY MANAGEMENT SYSTEM ===");
            System.out.println("1. Category Menu");
            System.out.println("2. Supplier Menu");
            System.out.println("3. Product Menu");
            System.out.println("4. Stock In Menu");
            System.out.println("5. Stock Out Menu");
            System.out.println("6. Dashboard Report");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> categoryMenu.show();
                case 2 -> supplierMenu.show();
                case 3 -> productMenu.show();
                case 4 -> stockInMenu.show();
                case 5 -> stockOutMenu.show();
                case 6 -> dashboardMenu.show();
                case 0 -> System.out.println("Application closed.");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }
}