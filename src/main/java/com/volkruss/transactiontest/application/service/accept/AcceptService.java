package com.volkruss.transactiontest.application.service.accept;

import com.volkruss.transactiontest.controller.AcceptController;

public interface AcceptService {
    void createAccept(AcceptController.RegistAcceptModel model) throws Exception;
}
