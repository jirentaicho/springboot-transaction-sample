package com.volkruss.transactiontest.application.service.stock.impl;

import com.volkruss.transactiontest.application.service.stock.StockOut;
import com.volkruss.transactiontest.application.service.stock.StockService;
import com.volkruss.transactiontest.controller.stcok.StockRequest;
import com.volkruss.transactiontest.domain.dto.stock.StockCriteria;
import com.volkruss.transactiontest.domain.model.stock.Stock;
import com.volkruss.transactiontest.domain.repository.stock.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<StockOut> index(StockCriteria criteria) {
        List<Stock> stocks = this.stockRepository.findAll(criteria);
        // map
        return stocks.stream().map(this::toOut).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public StockOut create(StockRequest request) {
        // id:0でもインサート時には自動採番されます
        Stock stock = new Stock(0,request.itemId,request.getCount());
        return this.toOut(this.stockRepository.save(stock));
    }

    @Override
    public void update() {

    }

    // TODO createMapper
    private StockOut toOut(Stock stock){
        StockOut out = new StockOut();
        out.setId(stock.getId());
        out.setItemId(stock.getItemId());
        out.setCount(stock.getCount());
        return out;
    }

}