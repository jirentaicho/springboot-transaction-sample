package com.volkruss.transactiontest.controller.stcok;

import lombok.Getter;
import lombok.Setter;

/**
 * GETでの利用想定
 * そのため単項目チェックは不要です
 */
@Getter
@Setter
public class StockQuery {
    private int itemId;
    private int count;
}
