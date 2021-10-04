package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private CategoryEntity category;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "photo_id")
    private Integer photoId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", insertable = false,updatable = false)
    private ImageEntity image;
}
