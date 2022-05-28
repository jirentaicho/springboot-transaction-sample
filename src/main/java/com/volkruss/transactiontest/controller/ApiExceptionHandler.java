package com.volkruss.transactiontest.controller;

import com.volkruss.transactiontest.domain.exception.ValidationException;
import com.volkruss.transactiontest.util.MessageUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public Resource validationException(Exception e, WebRequest request){

        ApiResource apiResource = new ApiResource();
        apiResource.setMessage("失敗");

        // 引数の例外クラスがValidationExceptionであることを保証させておく
        if(e instanceof ValidationException){
            ValidationException exception = (ValidationException) e;
            // ValidationExceptionのErrorsをここで捌く
            exception.getErrors().getAllErrors().forEach(i -> {
                apiResource.setMessages(
                        i.getCode(),
                        MessageUtils.getMessageSource().getMessage(i.getCode(),new String[]{}, null)
                );
            });
        }
        return apiResource;
    }

}
