package com.volkruss.transactiontest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    // AcceptControllerと同様で、あくまでテスト用でGETメソッドになっています。
    @GetMapping("/test/stock")
    public String test(){
        // Stockの更新
        // 業務的に単体で行うってのはどうなのか？って話はいったん置いときます。

        return "在庫更新完了";
    }

}
