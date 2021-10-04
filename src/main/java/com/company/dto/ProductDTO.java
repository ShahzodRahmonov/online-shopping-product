package com.company.dto;

import com.company.entity.CategoryEntity;
import com.company.entity.ImageEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer categoryId;
    private CategoryEntity category;
    private String description;
    private Double price;
    private Integer photoId;
    private ImageEntity image;
}
