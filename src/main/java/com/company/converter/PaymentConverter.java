package com.company.converter;

import com.company.dto.PaymentDTO;
import com.company.entity.PaymentEntity;

public class PaymentConverter {

    public static PaymentDTO toDTO(PaymentEntity entity){

        PaymentDTO dto = new PaymentDTO();

        dto.setId(entity.getId());
        dto.setInvoiceId(entity.getInvoiceId());
        dto.setAmount(entity.getAmount());
        dto.setTime(entity.getTime());

        return dto;
    }
}
