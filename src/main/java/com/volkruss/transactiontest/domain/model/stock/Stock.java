package com.volkruss.transactiontest.domain.model.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Stock {
    private final int id;
    private final int itemId;
    // そのままintを定義しています
    private int count;

    public void subCount(int sub){
        this.count -= sub;
    }

    public boolean isMinus(){
        return 0 > this.count;
    }
    public boolean willBeMinus(int subCount){
        return 0 > this.count - subCount;
    }
}
