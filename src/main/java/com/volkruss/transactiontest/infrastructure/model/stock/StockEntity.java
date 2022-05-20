package com.volkruss.transactiontest.infrastructure.model.stock;

import com.volkruss.transactiontest.infrastructure.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * データベース登録用の在庫DTO
 */
@Getter
@Setter
@Entity
@Table(name="stocks")
public class StockEntity implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int item_id;
    private int count;
}
