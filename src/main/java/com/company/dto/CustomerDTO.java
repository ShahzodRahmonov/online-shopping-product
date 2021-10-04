package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDTO {
    private Integer id;
    private String name;
    private String country;
    private String address;
    private String phone;
    private LocalDateTime createdDate;
}
