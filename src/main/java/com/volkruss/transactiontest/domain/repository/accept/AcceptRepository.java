package com.volkruss.transactiontest.domain.repository.accept;

import com.volkruss.transactiontest.domain.model.accept.Accept;


public interface AcceptRepository {
    void register(Accept accept);
}
