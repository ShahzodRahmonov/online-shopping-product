package com.company.controller;

import com.company.dto.CountCountry;
import com.company.dto.GetCustomersLastOrders;
import com.company.dto.GetOrderDetails;
import com.company.dto.OrderDTO;
import com.company.enums.OrderDetailStatus;
import com.company.servise.OrderServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServise orderServise;

//    create
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderDTO dto){
        OrderDTO response = this.orderServise.create(dto);
        return ResponseEntity.ok().body(response);
    }

    //    3. orders_without_details
    @GetMapping("/ordersWithoutDetails")
    public List<OrderDTO> ordersWithoutDetails(){
        List<OrderDTO> list = orderServise.ordersWithoutDetails(OrderDetailStatus.NO);
        return list;
    }

    //    GET order/details?order_id={order_id}
    @GetMapping("/details")
    public ResponseEntity<?> getOrderDetails(@RequestParam("orderId") Integer orderId){
        GetOrderDetails response = orderServise.getOrderDetails(orderId);
        return ResponseEntity.ok().body(response);
    }


    //    5. /customers_last_orders
    @GetMapping("/customersLastOrders")
    public ResponseEntity<?> customersLastOrders(){
        List<GetCustomersLastOrders> response = orderServise.customersLastOrders();
        return ResponseEntity.ok().body(response);
    }


    //    9. number_of_products_in_year
    @GetMapping("/countryCountOrders")
    public List<Object> countryCountOrders(){
        List<Object> response = orderServise.countryCountOrders();
        return response;
    }


    //    10. orders_without_invoices
    @GetMapping("/ordersWithoutInvoices")
    public List<Object> ordersWithoutInvoices(){
        List<Object> response = orderServise.ordersWithoutInvoices();
        return response;
    }


    
}
