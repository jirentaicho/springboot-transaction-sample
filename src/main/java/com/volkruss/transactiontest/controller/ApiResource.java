package com.volkruss.transactiontest.controller;

import com.volkruss.transactiontest.application.service.OutDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApiResource implements Resource{
    // 結果オブジェクト
    private List<? extends OutDto> result;

    // 結果メッセージ
    // TODO メッセージクラスの作成を行う
    private String message;

    public void setResultDate(List<? extends OutDto> list){
        this.result = list;
    }

    public List<? extends OutDto> getData(){
        return this.result.stream().collect(Collectors.toList());
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}
