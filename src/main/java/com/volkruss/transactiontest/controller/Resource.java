package com.volkruss.transactiontest.controller;

import com.volkruss.transactiontest.application.service.OutDto;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

/**
 * APIの戻り値のインターフェース
 */
public interface Resource {

    void setResultDate(List<? extends OutDto> list);

    List<? extends OutDto> getData();

    void setMessages(String key,String value);

    Map<String,String> getMessages();

}
