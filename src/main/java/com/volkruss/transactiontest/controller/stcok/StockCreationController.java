package com.volkruss.transactiontest.controller.stcok;

import com.volkruss.transactiontest.application.service.stock.StockOut;
import com.volkruss.transactiontest.controller.Resource;
import com.volkruss.transactiontest.domain.validator.stock.StockRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.Messager;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class StockCreationController extends StockController {

    @Autowired
    private StockRequestValidator stockRequestValidator;

    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(stockRequestValidator);
    }

    // 単項目チェックを行う
    @PostMapping("/stock/register")
    public Resource create(@Validated @RequestBody StockRequest request, BindingResult errors){
        if(errors.hasErrors()){
           errors.getFieldErrors()
                   .forEach(e -> {
                       // System.out.println(e.getField() + " : " + e.getDefaultMessage() + " : " + e.getCode());
                       // itemId : null : stock.test
                       // code 埋め込み文字列の配列　ロケール
                       System.out.println(this.messageSource.getMessage(e.getCode(),new String[]{}, null));
                   });
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
