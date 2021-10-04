package com.company.converter;

import com.company.dto.ImageDTO;
import com.company.entity.ImageEntity;

public class ImageConverter {

    public static ImageDTO toDTO(ImageEntity entity){

        ImageDTO dto = new ImageDTO();

        dto.setId(entity.getId());
        dto.setPath(entity.getPath());
        dto.setSize(entity.getSize());
        dto.setToken(entity.getToken());
        dto.setType(entity.getType());
        dto.setUrl(entity.getUrl());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
