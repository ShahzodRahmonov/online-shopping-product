package com.company.converter;

import com.company.dto.InvoiceDTO;
import com.company.entity.InvoiceEntity;

public class InvoiceConverter {

    public static InvoiceDTO toDTO(InvoiceEntity entity){

        InvoiceDTO dto = new InvoiceDTO();

        dto.setId(entity.getId());
        dto.setOrderId(dto.getOrderId());
        dto.setInvoiceStatus(entity.getInvoiceStatus());
        dto.setAmound(entity.getAmound());
        dto.setIssued(dto.getIssued());
        dto.setDue(entity.getDue());

        return dto;
    }
}
