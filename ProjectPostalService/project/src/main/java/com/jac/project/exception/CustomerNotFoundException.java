package com.jac.project.exception;

public class CustomerNotFoundException extends RuntimeException {
    private String message;

    public CustomerNotFoundException(String message){
        super(message);
    }
}
