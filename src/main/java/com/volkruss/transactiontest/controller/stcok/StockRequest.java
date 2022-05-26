package com.volkruss.transactiontest.controller.stcok;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Postされてくるときに渡されます
 * バリデーションも含めます
 */
@Getter
@Setter
public class StockRequest {
    public int id;
    @NotNull
    public int itemId;
    @NotNull
    public int count;
}
