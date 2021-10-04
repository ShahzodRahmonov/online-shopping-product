package com.company.exceptions;

public class CustomerExistsException extends RuntimeException{
    public CustomerExistsException(String message) {
        super(message);
    }
}
