package com.inventory.repository.interfaces;

import com.inventory.model.StockIn;

import java.util.List;

public interface IStockInRepository {

    void save(StockIn stockIn);

    List<StockIn> findAll();
}