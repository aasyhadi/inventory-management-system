package com.inventory.repository.interfaces;

import com.inventory.model.StockOut;

import java.util.List;

public interface IStockOutRepository {

    void save(StockOut stockOut);

    List<StockOut> findAll();
}