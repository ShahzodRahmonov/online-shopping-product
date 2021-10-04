package com.company.entity;

import com.company.enums.InvoiceStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ord_id")
    private Integer orderId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ord_id",insertable = false,updatable = false)
    private OrderEntity order;
    @Column(name = "amound")
    private Double amound;
    @Column(name = "issued")
    private LocalDateTime issued;
    @Column(name = "due")
    private LocalDateTime due;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InvoiceStatus invoiceStatus;
}
