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

    @Override
    public StockOut show(StockCriteria criteria) {
        // 扱いが難しい
        return this.toOut(this.stockRepository.find(criteria));
    }

    @Transactional
    @Override
    public StockOut create(StockRequest request) {
        // id:0でもインサート時には自動採番されます
        Stock stock = new Stock(0,request.itemId,request.getCount());
        return this.toOut(this.stockRepository.save(stock));
    }

    @Transactional
    @Override
    public StockOut update(StockRequest request) {
        // TODO findして取得する
        Stock stock = new Stock(request.getId(),request.getItemId(),request.getCount());
        return this.toOut(this.stockRepository.update(stock));
    }

    @Transactional
    @Override
    public void delete(StockRequest request) {
        StockCriteria criteria = new StockCriteria();
        criteria.setId(request.getId());
        Stock stock = this.stockRepository.find(criteria);
        this.stockRepository.delete(stock);
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