package com.volkruss.transactiontest.infrastructure.repository.stock;

import com.volkruss.transactiontest.domain.model.stock.Stock;
import com.volkruss.transactiontest.domain.repository.stock.StockRepository;
import com.volkruss.transactiontest.infrastructure.dao.stock.JpaStockDao;
import com.volkruss.transactiontest.infrastructure.model.stock.StockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockRepositoryImpl implements StockRepository {

    @Autowired
    private JpaStockDao<StockEntity> jpaStockDao;

    @Override
    public Stock findStockByItemId(int itemId) {
        StockEntity stockEntity = this.jpaStockDao.findByItemId(itemId);
        Stock stock = new Stock(stockEntity.getId(),stockEntity.getItem_id(),stockEntity.getCount());
        return stock;
    }

    @Override
    public void save(Stock stock) {
        // データベース用のオブジェクトに変換します
        StockEntity stockEntity = new StockEntity();
        stockEntity.setId(stock.getId());
        stockEntity.setItem_id(stock.getItemId());
        stockEntity.setCount(stock.getCount());
        this.jpaStockDao.update(stockEntity);
    }
}
