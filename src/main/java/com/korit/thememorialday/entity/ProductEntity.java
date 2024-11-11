package com.korit.thememorialday.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.korit.thememorialday.dto.request.product.PatchProductRequestDto;
import com.korit.thememorialday.dto.request.product.PostProductRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productNumber;
    private Integer storeNumber;
    private String productName;
    private String productIntroduce;
    private Integer productPrice;
    private boolean productToday;
    private String productTag;

    public ProductEntity(PostProductRequestDto dto, Integer storeNumber) {
        this.storeNumber = storeNumber;
        this.productName = dto.getProductName();
        this.productIntroduce = dto.getProductIntroduce();
        this.productPrice = dto.getProductPrice();
        this.productToday = dto.isProductToday();
        this.productTag = dto.getProductTag();
    }

    public void patch(PatchProductRequestDto dto) {
        this.productName = dto.getProductName();
        this.productIntroduce = dto.getProductIntroduce();
        this.productPrice = dto.getProductPrice();
        this.productToday = dto.isProductToday();
        this.productTag = dto.getProductTag();
    }

}
