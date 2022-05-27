package com.volkruss.transactiontest.domain.exception;

import org.springframework.validation.Errors;

import java.util.List;

public class ValidationException extends RuntimeException{

    private Errors errors;

    public ValidationException(Errors errors){
        super();
        this.errors = errors;
    }

    public Errors getErrors(){
        return this.errors;
    }

    public List<String> getMessages(){
        return null;
    }

}
