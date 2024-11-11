package com.korit.thememorialday.common.object.sales;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesData {
    private String orderCode;
    private String orderStatus;
    private String orderTime;
    private Integer totalPrice;
    private String productName;
    private String productImageUrl;
    private List<String> categories;
}
