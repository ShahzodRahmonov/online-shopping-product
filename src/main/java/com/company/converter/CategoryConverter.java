package com.company.converter;

import com.company.dto.CategoryDTO;
import com.company.entity.CategoryEntity;

public class CategoryConverter {
    public static CategoryDTO toDTO(CategoryEntity entity){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
