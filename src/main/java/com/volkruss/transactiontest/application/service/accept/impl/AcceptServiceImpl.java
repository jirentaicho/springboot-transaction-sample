package com.volkruss.transactiontest.application.service.accept.impl;

import com.volkruss.transactiontest.application.service.accept.AcceptService;
import com.volkruss.transactiontest.controller.AcceptController;
import com.volkruss.transactiontest.domain.model.accept.Accept;
import com.volkruss.transactiontest.domain.model.stock.Stock;
import com.volkruss.transactiontest.domain.repository.accept.AcceptRepository;
import com.volkruss.transactiontest.domain.repository.item.ItemRepository;
import com.volkruss.transactiontest.domain.repository.stock.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class AcceptServiceImpl implements AcceptService {

    @Autowired
    private AcceptRepository acceptRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PlatformTransactionManager txManager;

    @Override
    public void createAccept(AcceptController.RegistAcceptModel model) throws Exception {

        TransactionTemplate tm = new TransactionTemplate(txManager);
        tm.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        tm.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        tm.setTimeout(10);
        tm.setReadOnly(false);
        tm.execute((status) -> {
            // ドメインモデルの作成
            Accept accept = new Accept(model.itemId,model.customerName,model.count);
            // 受注の登録をする
            // わかりやすくロールバックを確認するためここで永続化します
            this.acceptRepository.register(accept);
            // 在庫の取得
            Stock stock = this.stockRepository.findStockByItemId(accept.getItemId());
            // 在庫を減らす(本当の業務なら実在庫を減らすことはせず予定在庫を減らすなど)
            stock.subCount(accept.getCount());
            // 在庫の整合性を確認する
            if(stock.isMinus()){
                // 適当な例外投げてます
                throw new RuntimeException();
            }
            // 在庫の更新をする
            this.stockRepository.save(stock);
            return DataAccessStatus.SUCCESS;
        });
    }

    /**
     * return nullの代用
     */
    public static enum DataAccessStatus{
        SUCCESS,
        ERROR
    }
}
