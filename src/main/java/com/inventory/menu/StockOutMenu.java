package com.inventory.menu;

import com.inventory.model.StockOut;
import com.inventory.service.StockOutService;

import java.util.List;
import java.util.Scanner;

public class StockOutMenu {

    private final StockOutService stockOutService;
    private final Scanner scanner;

    public StockOutMenu(
            StockOutService stockOutService,
            Scanner scanner) {

        this.stockOutService = stockOutService;
        this.scanner = scanner;
    }

    public void show() {

        int choice;

        do {

            System.out.println("\n=== STOCK OUT MENU ===");
            System.out.println("1. Stock Out Product");
            System.out.println("2. View Stock Out History");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            choice =
                    Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1 -> stockOutProduct();

                case 2 -> showHistory();

                case 0 ->
                        System.out.println("Back to Main Menu");

                default ->
                        System.out.println("Invalid Choice");
            }

        } while (choice != 0);
    }

    private void stockOutProduct() {

        try {

            System.out.print("Product ID : ");
            Long productId =
                    Long.parseLong(scanner.nextLine());

            System.out.print("Quantity : ");
            Integer quantity =
                    Integer.parseInt(scanner.nextLine());

            System.out.print("Note : ");
            String note =
                    scanner.nextLine();

            stockOutService.stockOut(
                    productId,
                    quantity,
                    note
            );

            System.out.println(
                    "Stock Out Success"
            );

        } catch (Exception e) {

            System.out.println(
                    "Error : " + e.getMessage()
            );
        }
    }

    private void showHistory() {

        List<StockOut> histories =
                stockOutService.getHistory();

        if (histories.isEmpty()) {

            System.out.println(
                    "No Stock Out History"
            );

            return;
        }

        System.out.println(
                "\n=== STOCK OUT HISTORY ==="
        );

        for (StockOut stockOut : histories) {

            System.out.println(
                    "ID : " + stockOut.getId()
                            + " | Product ID : " + stockOut.getProductId()
                            + " | Qty : " + stockOut.getQuantity()
                            + " | Note : " + stockOut.getNote()
            );
        }
    }
}