package com.company.controller;

import com.company.dto.PaymentDTO;
import com.company.servise.PaymentServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentServise paymentServise;

//    create pay
    @PostMapping("/pay")
    public ResponseEntity<?> pay(@RequestBody PaymentDTO dto){
        PaymentDTO response = paymentServise.pay(dto);
        return ResponseEntity.ok().body(response);
    }


    //    GET /payment/details?id={payment_details_id}
    @GetMapping("/details")
    public ResponseEntity<?> getPaymentDetailsById(@RequestParam("id") Integer id){
        PaymentDTO response = paymentServise.getPaymentDetailsById(id);
        return ResponseEntity.ok().body(response);
    }

}
