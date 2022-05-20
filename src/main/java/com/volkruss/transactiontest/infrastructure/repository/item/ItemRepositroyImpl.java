package com.volkruss.transactiontest.infrastructure.repository.item;

import com.volkruss.transactiontest.domain.model.item.Item;
import com.volkruss.transactiontest.domain.repository.item.ItemRepository;
import org.springframework.stereotype.Component;

@Component
public class ItemRepositroyImpl implements ItemRepository {
    @Override
    public Item findById(int id) {
        // DAOを使ってアイテムを取得する

        return null;
    }
}
