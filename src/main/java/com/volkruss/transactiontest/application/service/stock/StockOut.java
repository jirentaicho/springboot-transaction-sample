package com.volkruss.transactiontest.application.service.stock;

import com.volkruss.transactiontest.application.service.OutDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockOut implements OutDto {
    private int id;
    private int itemId;
    private int count;
}
