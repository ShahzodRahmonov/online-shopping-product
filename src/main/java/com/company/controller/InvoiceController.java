package com.company.controller;

import com.company.dto.InvoiceDTO;
import com.company.entity.InvoiceEntity;
import com.company.servise.InvoiceServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceServise invoiceServise;

//    invoice create
    @PostMapping("/create")
    public ResponseEntity<?> crete(@RequestBody InvoiceDTO dto){
        InvoiceDTO response = invoiceServise.create(dto);
        return ResponseEntity.ok().body(response);
    }

    //    1. expired_invoices
    @GetMapping("/expiredInvoices")
    public List<InvoiceDTO> expiredInvoices(){
        List<InvoiceDTO> list = invoiceServise.expiredInvoices();
        return list;
    }

    //    2. wrong_date_invoices
    @GetMapping("/wrongDateInvoices")
    ResponseEntity<?> wrongDateInvoices(){
        List<InvoiceDTO> response = invoiceServise.wrongDateInvoices();
        return ResponseEntity.ok().body(response);
    }

    //    6. overpaid_invoices
    @GetMapping("/overpaidInvoices")
    public ResponseEntity<List<InvoiceDTO>> overpaidInvoices(){
        List<InvoiceDTO> response = invoiceServise.overpaidInvoices();
        return ResponseEntity.ok().body(response);
    }
}
