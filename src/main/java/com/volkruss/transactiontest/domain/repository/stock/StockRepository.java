package com.volkruss.transactiontest.domain.repository.stock;


import com.volkruss.transactiontest.domain.dto.stock.StockCriteria;
import com.volkruss.transactiontest.domain.model.stock.Stock;

import java.util.List;

public interface StockRepository {
    List<Stock> findAll(StockCriteria criteria);
    Stock find(StockCriteria criteria);
    Stock findStockByItemId(int itemId);
    Stock save(Stock stock);
    Stock update(Stock stock);
}
