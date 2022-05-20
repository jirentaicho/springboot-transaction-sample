package com.volkruss.transactiontest.infrastructure.dao.base;

import com.volkruss.transactiontest.infrastructure.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 共通したSQLを実行できる基底クラス
 *
 * @param <T> テーブルに紐づくEntityクラス
 */
public abstract class BaseDao<T extends BaseEntity> {

    @PersistenceContext
    protected EntityManager em;

    public final void save(T t) {
        this.em.persist(t);
    }

    // 基本的にはEntityをnewしているので一度mergeします
    public final void update(T t){
        this.em.persist(this.em.merge(t));
    }
}
