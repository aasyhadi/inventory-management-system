package com.inventory.repository.impl;

import com.inventory.config.DatabaseConfig;
import com.inventory.model.StockIn;
import com.inventory.repository.interfaces.IStockInRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StockInRepositoryImpl
        implements IStockInRepository {

    @Override
    public void save(StockIn stockIn) {

        String sql = """
                INSERT INTO stock_ins
                (
                    product_id,
                    quantity,
                    note
                )
                VALUES
                (
                    ?,
                    ?,
                    ?
                )
                """;

        try (
                Connection conn =
                        DatabaseConfig.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setLong(1, stockIn.getProductId());
            ps.setInt(2, stockIn.getQuantity());
            ps.setString(3, stockIn.getNote());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StockIn> findAll() {

        List<StockIn> list = new ArrayList<>();

        String sql =
                "SELECT * FROM stock_ins ORDER BY id DESC";

        try (
                Connection conn =
                        DatabaseConfig.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                StockIn stockIn =
                        new StockIn();

                stockIn.setId(rs.getLong("id"));
                stockIn.setProductId(rs.getLong("product_id"));
                stockIn.setQuantity(rs.getInt("quantity"));
                stockIn.setNote(rs.getString("note"));

                list.add(stockIn);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}