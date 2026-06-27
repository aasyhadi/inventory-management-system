package com.inventory.repository.impl;

import com.inventory.config.DatabaseConfig;
import com.inventory.model.Category;
import com.inventory.repository.interfaces.ICategoryRepository;

import java.sql.*;
import java.util.ArrayList;

public class CategoryRepositoryImpl implements ICategoryRepository {

    @Override
    public void save(Category category) {
        String sql = "INSERT INTO categories (name) VALUES (?)";

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, category.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Category> findAll() {
        ArrayList<Category> categories = new ArrayList<>();

        String sql = "SELECT id, name FROM categories ORDER BY id ASC";

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                categories.add(new Category(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public Category findById(Long id) {
        String sql = "SELECT id, name FROM categories WHERE id = ?";

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Category(
                            resultSet.getLong("id"),
                            resultSet.getString("name")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Category findByName(String name) {
        String sql = "SELECT id, name FROM categories WHERE name = ?";

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Category(
                            resultSet.getLong("id"),
                            resultSet.getString("name")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean update(Long id, Category category) {
        String sql = "UPDATE categories SET name = ? WHERE id = ?";

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, category.getName());
            statement.setLong(2, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM categories WHERE id = ?";

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
                "SELECT COUNT(*) FROM categories";

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