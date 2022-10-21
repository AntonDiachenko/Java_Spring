package com.jac.project.exception;

public class CustomerExistException extends RuntimeException {
    private String message;

    public CustomerExistException(String message){
        super(message);
    }
}
