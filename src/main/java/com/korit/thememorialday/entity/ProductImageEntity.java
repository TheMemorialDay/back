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
    private Integer productNumber;
    private String productImageUrl;

    public ProductImageEntity(Integer productNumber, String productImageUrl) {
        this.productImageUrl = productImageUrl;
        this.productNumber = productNumber;
    }
}
