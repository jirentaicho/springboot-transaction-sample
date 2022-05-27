package com.volkruss.transactiontest.controller.stcok;

import com.volkruss.transactiontest.controller.Resource;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class StockDeleteController extends StockController{

    @PostMapping("/stock/delete")
    public Resource delete(@Validated @RequestBody StockRequest request, Errors errors){
        if(errors.hasErrors()){
            // TODO エラー処理
            System.out.println("エラーがあります");
        }
        this.stockService.delete(request);
        return this.toResource(Optional.empty(),"成功");
    }

}
