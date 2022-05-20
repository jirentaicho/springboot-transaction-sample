package com.volkruss.transactiontest.controller;

import com.volkruss.transactiontest.application.service.accept.AcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AcceptController {

    @Autowired
    private AcceptService acceptService;

    // 仮なのでGETで処理を行っています
    @GetMapping("/test/accept")
    public String result() throws Exception {
        // 仮の受注登録モデル(本来はリクエストの内容から作成します)
        RegistAcceptModel registModel = RegistAcceptModel.of(1,"婚后光子",100);    // 受注を登録する
        this.acceptService.createAccept(registModel);
        return "登録完了";
    }

    public static class RegistAcceptModel{
        public int itemId;
        public String customerName;
        public int count;
        public static RegistAcceptModel of(int id, String name,int count){
            RegistAcceptModel model = new RegistAcceptModel();
            model.itemId = id;
            model.customerName = name;
            model.count = count;
            return model;
        }
    }

}
