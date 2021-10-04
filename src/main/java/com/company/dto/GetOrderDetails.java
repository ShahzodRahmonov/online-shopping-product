package com.company.dto;

import com.company.entity.OrderEntity;
import com.company.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetOrderDetails {
    private Integer ordId;
    private Integer customerId;
    private List<String> productName;
}
