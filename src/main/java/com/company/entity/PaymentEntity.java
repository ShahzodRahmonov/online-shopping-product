package com.company.entity;

import com.company.enums.InvoiceStatus;
import com.company.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "inv_id")
    private Integer invoiceId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inv_id",insertable = false,updatable = false)
    private InvoiceEntity invoice;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus paymentStatus;
}
