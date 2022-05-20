package com.volkruss.transactiontest.infrastructure.repository.accept;

import com.volkruss.transactiontest.domain.model.accept.Accept;
import com.volkruss.transactiontest.domain.repository.accept.AcceptRepository;
import com.volkruss.transactiontest.infrastructure.dao.accept.JpaAcceptDao;
import com.volkruss.transactiontest.infrastructure.model.accept.AcceptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcceptRepositoryImpl implements AcceptRepository {

    @Autowired
    private JpaAcceptDao<AcceptEntity> acceptDao;

    @Override
    public void register(Accept accept) {
        //データベース登録用のデータに差し替えます
        AcceptEntity acceptEntity = new AcceptEntity();
        acceptEntity.setItem_id(accept.getItemId());
        acceptEntity.setCustomer_name(accept.getCustomerName());
        acceptEntity.setCount(accept.getCount());
        this.acceptDao.save(acceptEntity);
    }
}
