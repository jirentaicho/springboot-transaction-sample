package com.volkruss.transactiontest.domain.repository.stock;


import com.volkruss.transactiontest.domain.model.stock.Stock;

public interface StockRepository {
    Stock findStockByItemId(int itemId);
    void save(Stock stock);
}
