package com.company.converter;

import com.company.dto.CustomerDTO;
import com.company.entity.CustomerEntity;

public class CustomerConverter {
    public static CustomerDTO toDTO(CustomerEntity entity){

        CustomerDTO dto = new CustomerDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCountry(entity.getCountry());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }
}
