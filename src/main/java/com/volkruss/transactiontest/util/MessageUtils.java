package com.volkruss.transactiontest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {

    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource){
        MessageUtils.messageSource = messageSource;
    }

    public static MessageSource getMessageSource(){
        return MessageUtils.messageSource;
    }
}
