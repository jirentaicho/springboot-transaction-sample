package com.volkruss.transactiontest.infrastructure.dao.stock;

import com.volkruss.transactiontest.infrastructure.dao.base.BaseDao;
import com.volkruss.transactiontest.infrastructure.model.stock.StockEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaStockDao<T> extends BaseDao {

    public List<StockEntity> findAll(){
        return this.em.createQuery("select a from StockEntity a",StockEntity.class)
                .getResultList();
    }

    // 必要なSQLはスーパークラスで実装する
    public StockEntity findByItemId(int id){
        // fromは紐づくエンティティクラスを指定します
        // テーブル名を指定すると stocks is not mappedというエラーになります。
        return this.em.createQuery("select a from StockEntity a where a.item_id = :itemId",StockEntity.class)
                .setParameter("itemId", id)
                .getSingleResult();
    }
}
