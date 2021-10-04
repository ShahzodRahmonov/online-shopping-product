package com.company.exceptions;

public class ProdactNotFoundException extends RuntimeException{
    public ProdactNotFoundException(String message) {
        super(message);
    }
}
