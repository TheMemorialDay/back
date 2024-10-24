package com.korit.thememorialday.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_image")
public class ProductImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productImageNumber;

    @Column(name = "product_image_url", nullable = false)
    private String productImageUrl;

    @ManyToOne
    @JoinColumn(name = "product_number", nullable = false)
    private ProductEntity product;
}
