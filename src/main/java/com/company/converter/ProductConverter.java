package com.company.converter;

import com.company.dto.ProductDTO;
import com.company.entity.ProductEntity;

public class ProductConverter {
    public static ProductDTO toDTO(ProductEntity entity){

        ProductDTO dto = new ProductDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCategoryId(entity.getCategoryId());
        dto.setPrice(entity.getPrice());
        dto.setPhotoId(entity.getPhotoId());

        return dto;
    }
}
