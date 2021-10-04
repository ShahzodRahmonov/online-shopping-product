package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class GetCustomersLastOrders {
    private Integer customerId;
    private String customerName;
    private LocalDateTime orderTime;
}
