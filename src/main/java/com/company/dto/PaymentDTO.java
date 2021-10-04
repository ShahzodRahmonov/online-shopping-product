package com.company.dto;

import com.company.entity.InvoiceEntity;
import com.company.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PaymentDTO {
    private Integer id;
    private LocalDateTime time;
    private Double amount;
    private Integer invoiceId;
    private InvoiceEntity invoice;
    private PaymentStatus paymentStatus;
}
