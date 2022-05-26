package com.volkruss.transactiontest.controller.stcok;

import com.volkruss.transactiontest.domain.dto.stock.StockCriteria;

// いったんのマッパー
public class StockMapper {
    public static StockCriteria toCriteria(StockQuery query){
        StockCriteria criteria = new StockCriteria();
        criteria.setItem_id(query.getItemId());
        criteria.setCount(query.getCount());
        return criteria;
    }
}
