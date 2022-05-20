package com.volkruss.transactiontest.application.service.stock.impl;

import com.volkruss.transactiontest.application.service.stock.StockService;
import com.volkruss.transactiontest.domain.model.stock.Stock;
import com.volkruss.transactiontest.domain.repository.stock.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PlatformTransactionManager txManager;

    @Override
    public void update() {
        //　あくまでトランザクション確認用のメソッドです。
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); //新規トランザクションを開始
        def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        def.setTimeout(10);
        def.setReadOnly(false);
        TransactionStatus status = txManager.getTransaction(def);
        try{
            Stock stock = this.stockRepository.findStockByItemId(2);
            stock.subCount(10);
            stockRepository.save(stock);
        } catch (RuntimeException e){
            txManager.rollback(status);
            throw e;
        }
        txManager.commit(status);
    }
}
