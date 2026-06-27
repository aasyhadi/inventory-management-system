package com.inventory.menu;

import com.inventory.model.Category;
import com.inventory.service.CategoryService;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryMenu {

    private final CategoryService service;
    private final Scanner scanner;

    public CategoryMenu(
            CategoryService service,
            Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void show() {
        int choice;

        do {
            System.out.println("\n=== CATEGORY MENU ===");
            System.out.println("1. Add Category");
            System.out.println("2. View Categories");
            System.out.println("3. Update Category");
            System.out.println("4. Delete Category");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addCategory();
                case 2 -> viewCategories();
                case 3 -> updateCategory();
                case 4 -> deleteCategory();
                case 0 -> System.out.println("Back to main menu...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void addCategory() {
        try {
            System.out.print("Category Name: ");
            String name = scanner.nextLine();

            service.addCategory(new Category(name));

            System.out.println("Category added successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewCategories() {
        ArrayList<Category> categories =
                service.getAllCategories();

        System.out.println("\nID | Category Name");
        System.out.println("------------------");

        for (Category category : categories) {
            System.out.println(
                    category.getId() +
                            " | " +
                            category.getName()
            );
        }
    }

    private void updateCategory() {
        try {
            System.out.print("Category ID: ");
            Long id = Long.parseLong(scanner.nextLine());

            System.out.print("New Category Name: ");
            String name = scanner.nextLine();

            service.updateCategory(
                    id,
                    new Category(name)
            );

            System.out.println("Category updated successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteCategory() {
        try {
            System.out.print("Category ID: ");
            Long id = Long.parseLong(scanner.nextLine());

            service.deleteCategory(id);

            System.out.println("Category deleted successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}