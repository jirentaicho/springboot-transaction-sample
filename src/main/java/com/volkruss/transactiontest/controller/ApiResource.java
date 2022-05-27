package com.volkruss.transactiontest.controller;

import com.volkruss.transactiontest.application.service.OutDto;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ApiResource implements Resource{
    // 結果オブジェクト
    private List<? extends OutDto> result;

    // 結果メッセージ
    private Map<String,String> messages = new HashMap<>();

    // Errors
    private Errors errors;

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

    @Override
    public void setMessages(String key, String value) {
        this.messages.put(key,value);
    }

    @Override
    public Map<String, String> getMessages() {
        return this.messages;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}
