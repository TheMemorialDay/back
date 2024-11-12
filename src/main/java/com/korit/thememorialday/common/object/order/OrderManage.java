package com.korit.thememorialday.common.object.order;

import java.util.List;

import com.korit.thememorialday.entity.order.OrderEntity;

import lombok.Getter;

@Getter
public class OrderManage {
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
    private String cancelCode;
    private String cancelReason;
    private List<OrderSelectOption> options;
    private String name;
    private String telNumber;

    public OrderManage(OrderEntity order, List<OrderSelectOption> options, String storeName, String productName,
            String productImageUrl, String telNumber, String name) {
        this.orderCode = order.getOrderCode();
        this.orderStatus = order.getOrderStatus();
        this.storeNumber = order.getStoreNumber();
        this.orderTime = order.getOrderTime().toString();
        this.name = name;
        this.storeName = storeName;
        this.productName = productName;
        this.productContents = order.getProductContents();
        this.pickupTime = order.getPickupTime();
        this.totalPrice = order.getTotalPrice();
        this.options = options;
        this.productImageUrl = productImageUrl;
        this.cancelCode = order.getCancelCode();
        this.cancelReason = order.getCancelReason();
        this.telNumber = telNumber;
    }
}
