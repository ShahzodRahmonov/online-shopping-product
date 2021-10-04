package com.company.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({CustomerExistsException.class})
    public ResponseEntity<?> handlerException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<?> handlerException(CustomerNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({CAtegoryExistsException.class})
    public ResponseEntity<?> handlerException(CAtegoryExistsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<?> handlerException(OrderNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({InvoiceNotFoundException.class})
    public ResponseEntity<?> handlerException(InvoiceNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({InvoiceDueException.class})
    public ResponseEntity<?> handlerException(InvoiceDueException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({InvoiceStatusNotFailedException.class})
    public ResponseEntity<?> handlerException(InvoiceStatusNotFailedException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({CategoryNotFoundException.class})
    public ResponseEntity<?> handlerException(CategoryNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({ProdactNotFoundException.class})
    public ResponseEntity<?> handlerException(ProdactNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({ImageNotFoundException.class})
    public ResponseEntity<?> handlerException(ImageNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({DetailsNotFoundException.class})
    public ResponseEntity<?> handlerException(DetailsNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({PaymentNotFoundException.class})
    public ResponseEntity<?> handlerException(PaymentNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
