package com.volkruss.transactiontest.domain.model.accept;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Accept {
    private final int itemId;
    private final String customerName;
    private int count;

}
