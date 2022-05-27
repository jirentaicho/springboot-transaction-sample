package com.volkruss.transactiontest.controller.stcok;

import com.volkruss.transactiontest.application.service.stock.StockOut;
import com.volkruss.transactiontest.controller.Resource;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class StockCreationController extends StockController {

    // 単項目チェックを行う
    @PostMapping("/stock/register")
    public Resource create(@Validated @RequestBody StockRequest request, Errors errors){
        if(errors.hasErrors()){
            // TODO エラー処理
            System.out.println("エラーがあります");
        }
        // TODO updateでもおそらくinserOrUpdateなのでRepositoryの処理を統一できると思う
        StockOut stockOut = this.stockService.create(request);
        return this.toResource(Optional.of(List.of(stockOut)),"成功");
    }

    // TODO コントローラーを分割するかApiResourceの作成処理を抽出する
    @PostMapping("/stock/update")
    public Resource update(@Validated @RequestBody StockRequest request, Errors errors){
        if(errors.hasErrors()){
            // TODO エラー処理
            System.out.println("エラーがあります");
        }
        StockOut stockOut = this.stockService.update(request);
        return this.toResource(Optional.of(List.of(stockOut)),"成功");
    }


    // AcceptControllerと同様で、あくまでテスト用でGETメソッドになっています。
    @GetMapping("/test/stock")
    public String test(){
        // Stockの更新
        // 業務的に単体で行うってのはどうなのか？って話はいったん置いときます。

        return "在庫更新完了";
    }

}
