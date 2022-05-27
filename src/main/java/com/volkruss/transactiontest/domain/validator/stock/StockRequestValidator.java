package com.volkruss.transactiontest.domain.validator.stock;

import com.volkruss.transactiontest.controller.stcok.StockRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//コンポーネントスキャンの対象にする
@Component
public class StockRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        StockRequest request = (StockRequest)target;
        //　適当なバリデーションです(動きを見るためのもの)
        if(request.itemId==1 && request.getCount()==1){
            errors.rejectValue("itemId","stock.test");
        }

    }
}
