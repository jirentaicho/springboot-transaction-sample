package com.volkruss.transactiontest.infrastructure.repository.stock

import com.volkruss.transactiontest.domain.model.stock.Stock
import com.volkruss.transactiontest.infrastructure.dao.stock.JpaStockDao
import com.volkruss.transactiontest.infrastructure.model.stock.StockEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

//SpringBootのアノテーションが利用できる
@SpringBootTest
class StockRepositoryImplTest extends Specification {

    @Autowired
    StockRepositoryImpl stockRepository

    def "Stockクラスのオブジェクトが正しく取得できる"(){
        // 対象クラスの準備
        given: "Spyの設定とインジェクトする"
        // daoの戻り値を作成する
        def entity = new StockEntity()
        entity.id = 1
        entity.item_id = 1
        entity.count = 100
        // spyを利用する
        def jpaStockDao = Spy(JpaStockDao)
        // findByItemId(_)の戻り値をentityに設定する
        // any()は使わず_でその代用ができる
        jpaStockDao.findByItemId(_) >> entity
        // テスト対象のStockRepositoryImplのjpaStockDaoをspyしたオブジェクトに書き換える
        StockRepositoryImpl.metaClass.setAttribute(stockRepository,"jpaStockDao",jpaStockDao)

        when: "Stockの取得を行う"
        def result = stockRepository.findStockByItemId(1)

        then: "Stockクラスであること、値のマッピングを確認する"
        // Stockクラスであること。StockEntity→Stockへの変換ができていること
        result.class == Stock.class
        result.id == 1
        result.itemId == 1
        result.count == 100
    }
}
