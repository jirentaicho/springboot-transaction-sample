package com.volkruss.transactiontest.controller.stcok;

import com.volkruss.transactiontest.application.service.stock.StockOut;
import com.volkruss.transactiontest.application.service.stock.StockService;
import com.volkruss.transactiontest.controller.ApiResource;
import com.volkruss.transactiontest.controller.Resource;
import com.volkruss.transactiontest.domain.dto.stock.StockCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stock/index")
    public Resource index(StockQuery stockQuery){
        // QueryをCriteriaに変換する
        StockCriteria criteria = StockMapper.toCriteria(stockQuery);
        List<StockOut> stockOuts = stockService.index(criteria);
        // 返却用リソースモデルに変換して返します
        // TODO factoryを利用します
        ApiResource apiResource = new ApiResource();
        apiResource.setResultDate(stockOuts);
        apiResource.setMessage("成功");
        return apiResource;
    }

    //　1件の取得
    public Resource show(StockQuery stockQuery){
        return null;
    }


    // 単項目チェックを行う
    @PostMapping("/stock/register")
    public Resource create(@Validated @RequestBody StockRequest request, Errors errors){
        if(errors.hasErrors()){
            // TODO エラー処理
            System.out.println("エラーがあります");
        }
        StockOut stockOut = this.stockService.create(request);
        // TODO createFactory
        ApiResource apiResource = new ApiResource();
        apiResource.setResultDate(List.of(stockOut));
        apiResource.setMessage("成功");
        return apiResource;
    }

    // AcceptControllerと同様で、あくまでテスト用でGETメソッドになっています。
    @GetMapping("/test/stock")
    public String test(){
        // Stockの更新
        // 業務的に単体で行うってのはどうなのか？って話はいったん置いときます。

        return "在庫更新完了";
    }




}