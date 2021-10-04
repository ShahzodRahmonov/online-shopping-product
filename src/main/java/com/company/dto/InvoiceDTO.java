package com.company.dto;

import com.company.entity.OrderEntity;
import com.company.enums.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
    private Integer id;
    private Integer orderId;
    private OrderEntity order;
    private Double amound;
    private LocalDateTime issued;
    private LocalDateTime due;
    private InvoiceStatus invoiceStatus;
}
