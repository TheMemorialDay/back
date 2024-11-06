package com.korit.thememorialday.common.object;

import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PreviewProduct {
    private Integer productNumber;
    private String productName;
    private Integer productPrice;
    private boolean productToday;
    private List<String> themes;
    private String ProductImage;

    public PreviewProduct(Integer productNumber, String productName, Integer productPrice, 
    String productImage, List<String> themes, boolean productToday) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productToday = productToday;
        this.ProductImage = productImage;
        this.themes = themes;
    }
}
