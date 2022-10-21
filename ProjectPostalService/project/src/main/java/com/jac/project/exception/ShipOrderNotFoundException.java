package com.jac.project.exception;

public class ShipOrderNotFoundException extends RuntimeException{
    private String message;

    public ShipOrderNotFoundException(String message) {
        super(message);
    }

}
