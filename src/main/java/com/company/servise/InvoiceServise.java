package com.company.servise;

import com.company.converter.InvoiceConverter;
import com.company.dto.InvoiceDTO;
import com.company.entity.InvoiceEntity;
import com.company.entity.OrderEntity;
import com.company.enums.InvoiceStatus;
import com.company.exceptions.InvoiceNotFoundException;
import com.company.exceptions.OrderNotFoundException;
import com.company.repository.InvoiceRepository;
import com.company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvoiceServise {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private OrderRepository orderRepository;

    //create
    public InvoiceDTO create(InvoiceDTO dto){

        Optional<OrderEntity> optional = orderRepository.findById(dto.getOrderId());
        if (!optional.isPresent()) {
            throw new OrderNotFoundException("Order not found!!!");
        }

        Optional<InvoiceEntity> optional1 = invoiceRepository.findByOrderId(dto.getOrderId());
        if (optional1.isPresent()) {
            throw new InvoiceNotFoundException("There is an invoice for this order!!!");
        }

        //



        //

        InvoiceEntity entity = new InvoiceEntity();

        entity.setOrderId(dto.getOrderId());
        entity.setAmound(dto.getAmound());
        entity.setInvoiceStatus(InvoiceStatus.NOT_PAID);
        entity.setIssued(LocalDateTime.now());
        entity.setDue(entity.getIssued().plusMinutes(5));

        invoiceRepository.save(entity);

        dto.setId(entity.getId());
//        dto.setOrder(optional.get());
        dto.setIssued(entity.getIssued());
        dto.setDue(entity.getDue());
        dto.setInvoiceStatus(entity.getInvoiceStatus());

        return dto;
    }

//    1. expired_invoices
    public List<InvoiceDTO> expiredInvoices(){
        LocalDateTime currentTime = LocalDateTime.now();
        List<InvoiceEntity> list = invoiceRepository.findByDueBefore(currentTime);
        return list.stream().map(InvoiceConverter::toDTO).collect(Collectors.toList());
    }


//    2. wrong_date_invoices
    public List<InvoiceDTO> wrongDateInvoices(){
        List<InvoiceEntity> invoiceList = invoiceRepository.findAll();

        List<InvoiceEntity> list = new ArrayList<>();
        invoiceList.forEach(invoice -> {
            Optional<OrderEntity> optional = orderRepository.findById(invoice.getOrderId());
            OrderEntity order = optional.get();
            if (invoice.getIssued().isBefore(order.getDate())) {
                list.add(invoice);
            }
            if (list.isEmpty()){
                throw new InvoiceNotFoundException("Invoice not found!!!");
            }
        });
        return list.stream().map(InvoiceConverter::toDTO).collect(Collectors.toList());
    }


//    6. overpaid_invoices
    public List<InvoiceDTO> overpaidInvoices(){
        List<InvoiceEntity> list = invoiceRepository.overpaidInvoices();
        List<InvoiceDTO> dtoList = new LinkedList<>();
        list.forEach(invoiceEntity -> {

            InvoiceDTO dto = InvoiceDTO.builder().
                    id(invoiceEntity.getId()).amound(invoiceEntity.getAmound()).build();

            dtoList.add(dto);
        });

        return dtoList;
    }

}
