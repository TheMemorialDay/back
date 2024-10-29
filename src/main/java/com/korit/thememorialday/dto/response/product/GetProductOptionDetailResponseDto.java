package com.korit.thememorialday.dto.response.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.korit.thememorialday.entity.ProductOptionEntity;

@Getter
@Setter
@NoArgsConstructor
public class GetProductOptionDetailResponseDto {
    private String productCategory;
    private Integer productOptionPrice;

    public GetProductOptionDetailResponseDto(ProductOptionEntity productOptionEntity) {
        this.productCategory = productOptionEntity.getProductCategory();
        this.productOptionPrice = productOptionEntity.getProductOptionPrice();
    }
}
