package com.inventory.menu;

import com.inventory.model.StockIn;
import com.inventory.service.StockInService;

import java.util.List;
import java.util.Scanner;

public class StockInMenu {

    private final StockInService stockInService;
    private final Scanner scanner;

    public StockInMenu(
            StockInService stockInService,
            Scanner scanner) {

        this.stockInService = stockInService;
        this.scanner = scanner;
    }

    public void show() {
        int choice;

        do {
            System.out.println("\n=== STOCK IN MENU ===");
            System.out.println("1. Stock In Product");
            System.out.println("2. View Stock In History");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> stockInProduct();
                case 2 -> showHistory();
                case 0 -> System.out.println("Back to main menu.");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void stockInProduct() {
        try {
            System.out.print("Product ID: ");
            Long productId = Long.parseLong(scanner.nextLine());

            System.out.print("Quantity: ");
            Integer quantity = Integer.parseInt(scanner.nextLine());

            System.out.print("Note: ");
            String note = scanner.nextLine();

            stockInService.stockIn(
                    productId,
                    quantity,
                    note
            );

            System.out.println("Stock in success.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showHistory() {
        List<StockIn> histories =
                stockInService.getHistory();

        if (histories.isEmpty()) {
            System.out.println("No stock in history found.");
            return;
        }

        System.out.println("\n=== STOCK IN HISTORY ===");

        for (StockIn stockIn : histories) {
            System.out.println(
                    "ID: " + stockIn.getId()
                            + " | Product ID: " + stockIn.getProductId()
                            + " | Qty: " + stockIn.getQuantity()
                            + " | Note: " + stockIn.getNote()
            );
        }
    }
}