package com.company.entity;

import com.company.enums.InvoiceStatus;
import com.company.enums.OrderDetailStatus;
import com.company.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_date")
    private LocalDateTime date;
    @Column(name = "cust_id")
    private Integer customerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id",insertable = false,updatable = false)
    private CustomerEntity customer;
    @Enumerated(EnumType.STRING)
    @Column(name = "detail_status")
    private OrderDetailStatus detailStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
}
