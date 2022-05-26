package com.volkruss.transactiontest.controller;

import com.volkruss.transactiontest.application.service.OutDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    /**
     * 例えば、削除してここがnullの場合例外発生します
     * getDataがJsonオブジェクトとして返す時に呼ばれているから
     * @return
     */
    public List<? extends OutDto> getData(){
        if(Objects.isNull(this.result)){
            return this.result;
        }
        return this.result.stream().collect(Collectors.toList());
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}
