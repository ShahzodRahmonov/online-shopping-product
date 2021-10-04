package com.company.converter;

import com.company.dto.OrderDTO;
import com.company.entity.OrderEntity;

public class OrderConverter {

    public static OrderDTO toDTO(OrderEntity entity){

        OrderDTO dto = new OrderDTO();

        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setDate(entity.getDate());
        dto.setDetailStatus(entity.getDetailStatus());

        return dto;
    }
}
