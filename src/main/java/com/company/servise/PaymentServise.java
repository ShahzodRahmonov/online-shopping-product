package com.company.servise;

import com.company.converter.PaymentConverter;
import com.company.dto.PaymentDTO;
import com.company.entity.InvoiceEntity;
import com.company.entity.OrderEntity;
import com.company.entity.PaymentEntity;
import com.company.enums.InvoiceStatus;
import com.company.enums.OrderStatus;
import com.company.enums.PaymentStatus;
import com.company.exceptions.InvoiceDueException;
import com.company.exceptions.InvoiceNotFoundException;
import com.company.exceptions.InvoiceStatusNotFailedException;
import com.company.exceptions.PaymentNotFoundException;
import com.company.repository.InvoiceRepository;
import com.company.repository.OrderRepository;
import com.company.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServise {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private OrderRepository orderRepository;

//    create pay
    public PaymentDTO pay(PaymentDTO dto){

        Optional<InvoiceEntity> optional = invoiceRepository.findById(dto.getInvoiceId());
        if (!optional.isPresent()) {
            throw new InvoiceNotFoundException("Invoice not found!!!");
        }

        InvoiceEntity invoiceEntity = optional.get();
        LocalDateTime currentTime = LocalDateTime.now();

        if (invoiceEntity.getDue().isBefore(currentTime)){
            throw new InvoiceDueException("The deadline for payment has expired!!!");
        }

        PaymentEntity entity = new PaymentEntity();

        entity.setAmount(dto.getAmount());
        entity.setInvoiceId(dto.getInvoiceId());
        entity.setTime(LocalDateTime.now());
        entity.setPaymentStatus(PaymentStatus.SUCCESS);

        paymentRepository.save(entity);

        invoiceEntity.setDue(invoiceEntity.getDue().plusMinutes(5));
        invoiceRepository.save(invoiceEntity);

         Double amount = 0.0;
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(invoiceEntity.getOrderId());
        OrderEntity orderEntity = optionalOrderEntity.get();

        List<PaymentEntity> list = paymentRepository.findAllByInvoiceId(dto.getInvoiceId());

        for (PaymentEntity paymentEntity : list) {
            amount+=paymentEntity.getAmount();
        }
        if (amount>=invoiceEntity.getAmound()){
            invoiceEntity.setInvoiceStatus(InvoiceStatus.PAID);
            invoiceRepository.save(invoiceEntity);

            orderEntity.setOrderStatus(OrderStatus.SUCCESS);
            orderRepository.save(orderEntity);
        }

        dto.setId(entity.getId());
        dto.setTime(entity.getTime());
        dto.setPaymentStatus(entity.getPaymentStatus());
//        dto.setInvoice(entity.getInvoice());

        return dto;
    }


//    GET /payment/details?id={payment_details_id}
    public PaymentDTO getPaymentDetailsById(Integer id){

        Optional<PaymentEntity> optional = paymentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new PaymentNotFoundException("Payment not found!!!");
        }

        PaymentEntity entity = optional.get();
        return PaymentConverter.toDTO(entity);
    }
}
