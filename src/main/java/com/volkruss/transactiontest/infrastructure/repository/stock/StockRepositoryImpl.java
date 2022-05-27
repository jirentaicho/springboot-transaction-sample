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
        StockEntity stockEntity = this.toEntity(stock);
        this.jpaStockDao.save(stockEntity);
        return new Stock(stockEntity.getId(),stockEntity.getItem_id(),stockEntity.getCount());
    }

    @Override
    public Stock update(Stock stock) {
        StockEntity stockEntity = this.toEntity(stock);
        this.jpaStockDao.update(stockEntity);
        return new Stock(stockEntity.getId(),stockEntity.getItem_id(),stockEntity.getCount());

    }

    @Override
    public void delete(Stock stock) {
        StockEntity stockEntity = this.toEntity(stock);
        this.jpaStockDao.delete(stockEntity);
    }


    private StockEntity toEntity(Stock stock){
        StockEntity entity = new StockEntity();
        entity.setId(stock.getId());
        entity.setItem_id(stock.getItemId());
        entity.setCount(stock.getCount());
        return entity;
    }

    // TODO createMapper
    private Stock toStock(StockEntity stockEntity){
        return new Stock(
            stockEntity.getId(), stockEntity.getItem_id(), stockEntity.getCount());
    }

}
