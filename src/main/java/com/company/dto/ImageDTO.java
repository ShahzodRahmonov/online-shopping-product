package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDTO {

    private Integer id;
    private String url;
    private String path;
    private long size;
    private String type;
    private String token;
    private LocalDateTime createdDate;
}
