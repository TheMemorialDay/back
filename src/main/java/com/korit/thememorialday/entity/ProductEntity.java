package com.korit.thememorialday.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productNumber;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_introduce")
    private String productIntroduce;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Column(name = "product_today", nullable = false)
    private boolean productToday;

    @Column(name = "product_tag", nullable = false)
    private String productTag;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductMappingEntity> options = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ThemaEntity> themes = new ArrayList<>();
}
