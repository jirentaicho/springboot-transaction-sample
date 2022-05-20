package com.volkruss.transactiontest.domain.repository.item;

import com.volkruss.transactiontest.domain.model.item.Item;

public interface ItemRepository {
    public Item findById(int id);
}
