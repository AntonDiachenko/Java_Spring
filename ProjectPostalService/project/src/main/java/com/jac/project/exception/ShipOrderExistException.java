package com.jac.project.exception;

public class ShipOrderExistException extends RuntimeException{
    private String message;

    public ShipOrderExistException(String message) {
        super(message);
    }
}
