package com.company.exceptions;

public class DetailsNotFoundException extends RuntimeException{
    public DetailsNotFoundException(String message) {
        super(message);
    }
}
