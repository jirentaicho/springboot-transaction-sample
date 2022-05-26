package com.volkruss.transactiontest.application.service.stock;

import com.volkruss.transactiontest.controller.stcok.StockRequest;
import com.volkruss.transactiontest.domain.dto.stock.StockCriteria;
import com.volkruss.transactiontest.domain.model.stock.Stock;

import java.util.List;

public interface StockService {
    List<StockOut> index(StockCriteria criteria);
    StockOut show(StockCriteria criteria);
    StockOut create(StockRequest request);
    void update();
}

