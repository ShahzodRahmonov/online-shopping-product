package com.company.controller;

import com.company.dto.CustomerDTO;
import com.company.servise.CustomerServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerServise customerServise;

    //create
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CustomerDTO dto){
    CustomerDTO response = customerServise.create(dto);
    return ResponseEntity.ok().body(response);
    }

//    get id
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        CustomerDTO response = this.customerServise.getById(id);
        return ResponseEntity.ok().body(response);
    }

    //    4. customers_without_orders
    @GetMapping("/customersWithoutOrders")
    public ResponseEntity<?> customersWithoutOrders(){
        List<CustomerDTO> response = customerServise.customersWithoutOrders();
        return ResponseEntity.ok().body(response);
    }



}
