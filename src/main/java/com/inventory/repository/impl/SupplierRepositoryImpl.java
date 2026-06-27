package com.inventory.repository.impl;

import com.inventory.config.DatabaseConfig;
import com.inventory.model.Supplier;
import com.inventory.repository.interfaces.ISupplierRepository;

import java.sql.*;
import java.util.ArrayList;

public class SupplierRepositoryImpl implements ISupplierRepository {

    @Override
    public void save(Supplier supplier) {
        String sql = """
                INSERT INTO suppliers (name, phone, address)
                VALUES (?, ?, ?)
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getPhone());
            statement.setString(3, supplier.getAddress());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Supplier> findAll() {
        ArrayList<Supplier> suppliers = new ArrayList<>();

        String sql = """
                SELECT id, name, phone, address
                FROM suppliers
                ORDER BY id ASC
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                suppliers.add(new Supplier(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return suppliers;
    }

    @Override
    public Supplier findById(Long id) {
        String sql = """
                SELECT id, name, phone, address
                FROM suppliers
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
                    return new Supplier(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("phone"),
                            resultSet.getString("address")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Supplier findByName(String name) {
        String sql = """
                SELECT id, name, phone, address
                FROM suppliers
                WHERE name = ?
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Supplier(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("phone"),
                            resultSet.getString("address")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean update(Long id, Supplier supplier) {
        String sql = """
                UPDATE suppliers
                SET name = ?, phone = ?, address = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getPhone());
            statement.setString(3, supplier.getAddress());
            statement.setLong(4, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM suppliers WHERE id = ?";

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

    @Override
    public long count() {

        String sql =
                "SELECT COUNT(*) FROM suppliers";

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

}