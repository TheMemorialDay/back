package com.korit.thememorialday.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public ProductImageEntity(String productImageUrl, ProductEntity product) {
        this.productImageUrl = productImageUrl;
        this.product = product;
    }
}
