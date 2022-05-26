package com.volkruss.transactiontest.infrastructure.repository.stock;

import com.volkruss.transactiontest.domain.dto.stock.StockCriteria;
import com.volkruss.transactiontest.domain.model.stock.Stock;
import com.volkruss.transactiontest.domain.repository.stock.StockRepository;
import com.volkruss.transactiontest.infrastructure.dao.stock.JpaStockDao;
import com.volkruss.transactiontest.infrastructure.model.stock.StockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockRepositoryImpl implements StockRepository {

    @Autowired
    private JpaStockDao<StockEntity> jpaStockDao;

    @Override
    public List<Stock> findAll(StockCriteria criteria) {
        List<StockEntity> stockEntities = this.jpaStockDao.findAll();
        return stockEntities.stream().map(this::toStock).collect(Collectors.toList());
    }

    @Override
    public Stock find(StockCriteria criteria) {
        StockEntity stockEntity = (StockEntity) this.jpaStockDao.find(StockEntity.class,criteria.getId());
        return this.toStock(stockEntity);
    }

    @Override
    public Stock findStockByItemId(int itemId) {
        StockEntity stockEntity = this.jpaStockDao.findByItemId(itemId);
        Stock stock = new Stock(stockEntity.getId(),stockEntity.getItem_id(),stockEntity.getCount());
        return stock;

    }

    @Override
    public Stock save(Stock stock) {
        // データベース用のオブジェクトに変換します
        StockEntity stockEntity = new StockEntity();
        stockEntity.setId(stock.getId());
        stockEntity.setItem_id(stock.getItemId());
        stockEntity.setCount(stock.getCount());
        this.jpaStockDao.save(stockEntity);
        return new Stock(stockEntity.getId(),stockEntity.getItem_id(),stockEntity.getCount());
    }

    @Override
    public Stock update(Stock stock) {
        // TODO mapperの作成
        StockEntity stockEntity = new StockEntity();
        stockEntity.setId(stock.getId());
        stockEntity.setItem_id(stock.getItemId());
        stockEntity.setCount(stock.getCount());
        this.jpaStockDao.update(stockEntity);
        return new Stock(stockEntity.getId(),stockEntity.getItem_id(),stockEntity.getCount());

    }

    // TODO createMapper
    private Stock toStock(StockEntity stockEntity){
        return new Stock(
            stockEntity.getId(), stockEntity.getItem_id(), stockEntity.getCount());
    }

}
