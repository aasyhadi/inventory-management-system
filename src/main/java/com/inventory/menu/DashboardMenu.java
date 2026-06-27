package com.inventory.menu;

import com.inventory.service.DashboardService;

public class DashboardMenu {

    private final DashboardService dashboardService;

    public DashboardMenu(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    public void show() {

        System.out.println();
        System.out.println("========== DASHBOARD ==========");

        System.out.println(
                "Total Categories : "
                        + dashboardService.totalCategories());

        System.out.println(
                "Total Suppliers  : "
                        + dashboardService.totalSuppliers());

        System.out.println(
                "Total Products   : "
                        + dashboardService.totalProducts());

        System.out.println(
                "Inventory Value  : Rp "
                        + String.format(
                        "%,.0f",
                        dashboardService.inventoryValue()
                ));

        System.out.println("===============================");
    }
}