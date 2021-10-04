package com.company.dto;

import com.company.entity.OrderEntity;
import com.company.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DetailDTO {
    private Integer id;
    private Integer ordId;
    private OrderEntity order;
    private Integer productId;
    private ProductEntity product;
    private Integer quantity;
}
