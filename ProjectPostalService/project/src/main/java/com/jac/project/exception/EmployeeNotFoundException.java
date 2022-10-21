package com.jac.project.exception;

public class EmployeeNotFoundException extends RuntimeException{
    private String message;
    public EmployeeNotFoundException(String message){
        super(message);
    }
}
