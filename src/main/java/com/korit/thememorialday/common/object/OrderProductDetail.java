package com.korit.thememorialday.common.object;
import java.util.List;


import com.korit.thememorialday.entity.ProductEntity;
import com.korit.thememorialday.entity.ProductOptionEntity;
import com.korit.thememorialday.entity.StoreEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDetail {
    private Integer productNumber; 
    private String productName;
    private String StoreCaution; 
    private String productIntroduce;
    private Integer productPrice;
    private boolean productToday;
    private List<String> productImages;
    private List<String> themes;
    private List<Option> options;

    private String mondayOpen;
    private String mondayLast;
    private String tuesdayOpen;
    private String tuesdayLast;
    private String wednesdayOpen;
    private String wednesdayLast;
    private String thursdayOpen;
    private String thursdayLast;
    private String fridayOpen;
    private String fridayLast;
    private String saturdayOpen;
    private String saturdayLast;
    private String sundayOpen;
    private String sundayLast;

    public OrderProductDetail(ProductEntity productEntity, List<String> productImages, 
        List<String> themes, List<Option> options, StoreEntity storeEntity) {
        this.productNumber = productEntity.getProductNumber();
        this.productName = productEntity.getProductName();
        this.productIntroduce = productEntity.getProductIntroduce();
        this.productPrice = productEntity.getProductPrice();
        this.productToday = productEntity.isProductToday();
        this.productImages = productImages;
        this.options = options;
        this.themes = themes;
        this.StoreCaution = storeEntity.getStoreCaution();

        this.mondayOpen = storeEntity.getMondayOpen();
        this.mondayLast = storeEntity.getMondayLast();
        this.tuesdayOpen = storeEntity.getTuesdayOpen();
        this.tuesdayLast = storeEntity.getTuesdayLast();
        this.wednesdayOpen = storeEntity.getWednesdayOpen();
        this.wednesdayLast = storeEntity.getWednesdayLast();
        this.thursdayOpen = storeEntity.getThursdayOpen();
        this.thursdayLast = storeEntity.getThursdayLast();
        this.fridayOpen = storeEntity.getFridayOpen();
        this.fridayLast = storeEntity.getFridayLast();
        this.saturdayOpen = storeEntity.getSaturdayOpen();
        this.saturdayLast = storeEntity.getSaturdayLast();
        this.sundayOpen = storeEntity.getSundayOpen();
        this.sundayLast = storeEntity.getSundayLast();
    }
}
