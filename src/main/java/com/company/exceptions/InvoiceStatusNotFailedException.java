package com.company.exceptions;

public class InvoiceStatusNotFailedException extends RuntimeException{
    public InvoiceStatusNotFailedException(String message) {
        super(message);
    }
}
