package com.company.exceptions;

public class InvoiceDueException extends RuntimeException{
    public InvoiceDueException(String message) {
        super(message);
    }
}
