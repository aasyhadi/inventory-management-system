package com.inventory.repository.impl;

import com.inventory.config.DatabaseConfig;
import com.inventory.model.Product;
import com.inventory.repository.interfaces.IProductRepository;

import java.sql.*;
import java.util.ArrayList;

public class ProductRepositoryImpl implements IProductRepository {

    @Override
    public void save(Product product) {
        String sql = """
                INSERT INTO products
                (sku, name, category_id, supplier_id, price, stock, minimum_stock)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, product.getSku());
            statement.setString(2, product.getName());
            statement.setLong(3, product.getCategoryId());
            statement.setLong(4, product.getSupplierId());
            statement.setBigDecimal(5, product.getPrice());
            statement.setInt(6, product.getStock());
            statement.setInt(7, product.getMinimumStock());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();

        String sql = """
                SELECT id, sku, name, category_id, supplier_id,
                       price, stock, minimum_stock
                FROM products
                ORDER BY id ASC
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                products.add(mapResultSetToProduct(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public Product findById(Long id) {
        String sql = """
                SELECT id, sku, name, category_id, supplier_id,
                       price, stock, minimum_stock
                FROM products
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToProduct(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Product findBySku(String sku) {
        String sql = """
                SELECT id, sku, name, category_id, supplier_id,
                       price, stock, minimum_stock
                FROM products
                WHERE sku = ?
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, sku);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToProduct(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean update(Long id, Product product) {
        String sql = """
                UPDATE products
                SET sku = ?,
                    name = ?,
                    category_id = ?,
                    supplier_id = ?,
                    price = ?,
                    stock = ?,
                    minimum_stock = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, product.getSku());
            statement.setString(2, product.getName());
            statement.setLong(3, product.getCategoryId());
            statement.setLong(4, product.getSupplierId());
            statement.setBigDecimal(5, product.getPrice());
            statement.setInt(6, product.getStock());
            statement.setInt(7, product.getMinimumStock());
            statement.setLong(8, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Product mapResultSetToProduct(ResultSet resultSet)
            throws SQLException {

        return new Product(
                resultSet.getLong("id"),
                resultSet.getString("sku"),
                resultSet.getString("name"),
                resultSet.getLong("category_id"),
                resultSet.getLong("supplier_id"),
                resultSet.getBigDecimal("price"),
                resultSet.getInt("stock"),
                resultSet.getInt("minimum_stock")
        );
    }

    @Override
    public long count() {

        String sql =
                "SELECT COUNT(*) FROM products";

        try (
                Connection conn =
                        DatabaseConfig.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getLong(1);
            }

            return 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public double totalInventoryValue() {

        String sql =
                """
                SELECT SUM(price * stock)
                FROM products
                """;

        try (
                Connection conn =
                        DatabaseConfig.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

            return 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}