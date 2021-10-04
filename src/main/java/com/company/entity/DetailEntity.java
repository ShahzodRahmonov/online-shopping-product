package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "detail")
public class DetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ord_id")
    private Integer orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ord_id",insertable = false,updatable = false)
    private OrderEntity order;
    @Column(name = "pr_id")
    private Integer productId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_id",insertable = false,updatable = false)
    private ProductEntity product;
    @Column(name = "quantity")
    private Integer quantity;
}
