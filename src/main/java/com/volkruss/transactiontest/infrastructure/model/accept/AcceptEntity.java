package com.volkruss.transactiontest.infrastructure.model.accept;

import com.volkruss.transactiontest.infrastructure.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * データベース登録用の受注DTO
 */
@Getter
@Setter
@Entity
@Table(name="accept")
public class AcceptEntity implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動的にidの値がMax + 1になります
    public int id;
    public int item_id;
    public String customer_name;
    public int count;
}
