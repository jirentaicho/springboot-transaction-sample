package com.volkruss.transactiontest.controller.stcok;

import com.volkruss.transactiontest.application.service.stock.StockOut;
import com.volkruss.transactiontest.application.service.stock.StockService;
import com.volkruss.transactiontest.controller.ApiResource;
import com.volkruss.transactiontest.controller.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class StockController {

    @Autowired
    protected StockService stockService;

    protected Resource toResource(Optional<List<StockOut>> optionalStockOuts,String message){
        ApiResource apiResource = new ApiResource();
        if(optionalStockOuts.isPresent()){
            apiResource.setResultDate(optionalStockOuts.get());
        }
        apiResource.setMessage(message);
        return apiResource;
    }
}
