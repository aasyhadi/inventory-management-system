# Inventory Management System

Inventory Management System adalah aplikasi berbasis Java Console yang digunakan untuk mengelola kategori, supplier, produk, stok masuk, stok keluar, dan laporan inventori.

Project ini dibuat sebagai portfolio Java Backend Developer dengan menerapkan konsep Object-Oriented Programming (OOP), Layered Architecture, Repository Pattern, Service Layer, Validation, JDBC, MySQL, serta Unit Testing menggunakan JUnit dan Mockito.

---

## Tech Stack

* Java 21
* Maven
* MySQL
* JDBC
* JUnit 5
* Mockito
* IntelliJ IDEA

---

## Features

### Master Data

* Category Management
* Supplier Management
* Product Management

### Inventory Transaction

* Stock In
* Stock Out
* Stock History

### Reporting

* Dashboard Report
* Low Stock Product Monitoring
* Total Inventory Value

### Testing

* ProductServiceTest
* StockInServiceTest
* StockOutServiceTest

---

## Project Structure

```text
src/main/java/com/inventory
├── config
├── exception
├── menu
├── model
├── repository
│   ├── interfaces
│   └── impl
├── service
├── validation
└── Main.java
```

---

## Database Schema

### Create Database

```sql
CREATE DATABASE inventory_db;
```

### Tables

```text
categories
suppliers
products
stock_ins
stock_outs
```

---

## Entity Relationship Diagram (ERD)

```text
categories
    │
    │ 1
    │
    └───────< products >───────┐
                               │
                               │
                               │
suppliers                      │
    │                          │
    │ 1                        │
    └──────────────────────────┘

products
    │
    ├──────────< stock_ins
    │
    └──────────< stock_outs
```

---

## Main Modules

### Category

Mengelola data kategori produk.

### Supplier

Mengelola data supplier.

### Product

Mengelola data produk yang terdiri dari:

* SKU
* Nama Produk
* Harga
* Stok
* Minimum Stok
* Kategori
* Supplier

### Stock In

Menambah stok produk dan mencatat histori barang masuk.

### Stock Out

Mengurangi stok produk dan mencatat histori barang keluar.

### Dashboard

Menampilkan ringkasan inventori:

* Total Categories
* Total Suppliers
* Total Products
* Total Inventory Value

---

## How To Run

### Clone Repository

```bash
git clone https://github.com/username/inventory-management-system.git
cd inventory-management-system
```

### Configure Database

Edit file:

```text
src/main/java/com/inventory/config/DatabaseConfig.java
```

Contoh konfigurasi:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/inventory_db";

private static final String USERNAME =
        "root";

private static final String PASSWORD =
        "root";
```

### Run Application

```bash
mvn clean compile
mvn exec:java
```

Atau langsung jalankan:

```text
Main.java
```

dari IntelliJ IDEA.

---

## Running Tests

Menjalankan seluruh unit test:

```bash
mvn test
```

Contoh hasil:

```text
ProductServiceTest      PASS
StockInServiceTest      PASS
StockOutServiceTest     PASS

BUILD SUCCESS
```

---

## Sample Menu

```text
=== INVENTORY MANAGEMENT SYSTEM ===

1. Category Menu
2. Supplier Menu
3. Product Menu
4. Stock In Menu
5. Stock Out Menu
6. Dashboard Report

0. Exit
```

---

## Design Pattern Used

* Repository Pattern
* Service Layer Pattern
* Dependency Injection
* Validation Layer
* Layered Architecture

---

## Future Improvements

* Authentication & Authorization
* Export Excel Report
* REST API Version (Spring Boot)
* Docker Deployment
* Inventory Audit Log
* Purchase Order Module
* Sales Module

---

## Author

Ahmad Asyhadi, Java Backend Developer Portfolio Project
Built with Java 21, JDBC, MySQL, JUnit 5, and Mockito.
