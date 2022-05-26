package com.volkruss.transactiontest.controller;

import com.volkruss.transactiontest.application.service.OutDto;

import java.util.List;

/**
 * APIの戻り値のインターフェース
 */
public interface Resource {

    void setResultDate(List<? extends OutDto> list);

    List<? extends OutDto> getData();

}
