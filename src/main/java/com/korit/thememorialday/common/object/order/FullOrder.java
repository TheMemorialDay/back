package com.korit.thememorialday.common.object.order;

import java.util.List;

import com.korit.thememorialday.entity.order.OrderEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullOrder {
    private String orderCode;
    private String orderStatus;
    private String orderTime;
    private Integer storeNumber;
    private String storeName;
    private String productName;
    private String productContents;
    private String pickupTime;
    private Integer totalPrice;
    private String productImageUrl;
    private List<OrderSelectOption> options;

    public FullOrder(OrderEntity order, List<OrderSelectOption> options, String storeName, String productName, String productImageUrl) {
        this.orderCode = order.getOrderCode();
        this.orderStatus = order.getOrderStatus();
        this.storeNumber = order.getStoreNumber();
        this.orderTime = order.getOrderTime().toString();
        this.storeName = storeName;
        this.productName = productName;
        this.productContents = order.getProductContents();
        this.pickupTime = order.getPickupTime();
        this.totalPrice = order.getTotalPrice();
        this.options = options;
        this.productImageUrl = productImageUrl;
    }
}



// mypage에서 get 할 때 사용하셔도 됩니당