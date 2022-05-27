package com.volkruss.transactiontest.controller.stcok;

import com.volkruss.transactiontest.application.service.stock.StockOut;
import com.volkruss.transactiontest.controller.Resource;
import com.volkruss.transactiontest.domain.dto.stock.StockCriteria;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class StockReferenceController extends StockController{

    @GetMapping("/stock/index")
    public Resource index(StockQuery stockQuery){
        // QueryをCriteriaに変換する
        StockCriteria criteria = StockMapper.toCriteria(stockQuery);
        List<StockOut> stockOuts = stockService.index(criteria);
        return toResource(Optional.of(stockOuts),"成功");
    }

    //　1件の取得
    @GetMapping("/stock/show/{id}")
    public Resource show(@PathVariable int id){
        // TODO この辺は見直し
        StockQuery query = new StockQuery();
        query.setId(id);
        // QueryをCriteriaに変換する
        StockCriteria criteria = StockMapper.toCriteria(query);
        StockOut stockOut = stockService.show(criteria);
        return toResource(Optional.of(List.of(stockOut)),"成功");
    }
}
