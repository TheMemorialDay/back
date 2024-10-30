package com.korit.thememorialday.common.object;

import java.util.List;

import com.korit.thememorialday.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullProduct {
    private Integer productNumber;
    private String productName;
    private String productIntroduce;
    private Integer productPrice;
    private boolean productToday;
    private String productTag;
    private String imageUrl;
    private List<String> productImages; // 이미지 URL 리스트
    private List<Option> options; // 옵션 리스트
    private List<String> themes; // 테마 리스트

    public FullProduct(ProductEntity productEntity, List<String> productImages, List<String> themes, List<Option> options) {
        String imageUrl = productImages.size() != 0 ? productImages.get(0) : null;
        this.productNumber = productEntity.getProductNumber();
        this.productName = productEntity.getProductName();
        this.productIntroduce = productEntity.getProductIntroduce();
        this.productPrice = productEntity.getProductPrice();
        this.productToday = productEntity.isProductToday();
        this.productTag = productEntity.getProductTag();
        this.imageUrl = imageUrl;
        this.productImages = productImages;
        this.options = options;
        this.themes = themes;
    }
}
