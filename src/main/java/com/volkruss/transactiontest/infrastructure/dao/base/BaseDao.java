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

    public final T find(Class<T> clazz, int id){
        return this.em.find(clazz,id);
    }

    public final T save(T t) {
        this.em.persist(t);
        // 引数のEntity自体を破壊的に更新します
        this.em.refresh(t);
        return t;
    }

    // 基本的にはEntityをnewしているので一度mergeします
    public final T update(T t){
        this.em.persist(this.em.merge(t));
        // this.em.refresh(t);
        return t;
    }
}
