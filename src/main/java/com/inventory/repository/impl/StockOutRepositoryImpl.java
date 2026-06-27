package com.inventory.repository.impl;

import com.inventory.config.DatabaseConfig;
import com.inventory.model.StockOut;
import com.inventory.repository.interfaces.IStockOutRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StockOutRepositoryImpl
        implements IStockOutRepository {

    @Override
    public void save(StockOut stockOut) {

        String sql = """
                INSERT INTO stock_outs
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

            ps.setLong(1, stockOut.getProductId());
            ps.setInt(2, stockOut.getQuantity());
            ps.setString(3, stockOut.getNote());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StockOut> findAll() {

        List<StockOut> list = new ArrayList<>();

        String sql =
                "SELECT * FROM stock_outs ORDER BY id DESC";

        try (
                Connection conn =
                        DatabaseConfig.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                StockOut stockOut =
                        new StockOut();

                stockOut.setId(rs.getLong("id"));
                stockOut.setProductId(rs.getLong("product_id"));
                stockOut.setQuantity(rs.getInt("quantity"));
                stockOut.setNote(rs.getString("note"));

                list.add(stockOut);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}