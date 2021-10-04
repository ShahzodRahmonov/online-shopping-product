package com.company.dto;

import com.company.entity.CustomerEntity;
import com.company.enums.OrderDetailStatus;
import com.company.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class OrderDTO {
    private Integer id;
    private LocalDateTime date;
    private Integer customerId;
    private CustomerEntity customer;
    private OrderDetailStatus detailStatus;
    private OrderStatus orderStatus;
}
