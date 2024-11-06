package com.korit.thememorialday.common.object.order;

import java.util.List;
import com.korit.thememorialday.entity.order.OrderEntity;
import java.util.ArrayList;
import lombok.Getter;

@Getter
public class Order {
    private String orderCode;
    private Integer productNumber;
    private Integer storeNumber;
    private String userId;
    private String productContents;
    private String pickupTime;
    private String orderStatus;
    private Integer productCount;
    private Integer totalPrice;
    private String optionSelect;

    private Order(OrderEntity orderEntity) {
        this.orderCode = orderEntity.getOrderCode();
        this.productNumber = orderEntity.getProductNumber();
        this.storeNumber = orderEntity.getStoreNumber();
        this.userId = orderEntity.getUserId();
        this.productContents = orderEntity.getProductContents();
        this.pickupTime = orderEntity.getPickupTime();
        this.orderStatus = orderEntity.getOrderStatus();
        this.productCount = orderEntity.getProductCount();
        this.totalPrice = orderEntity.getTotalPrice();
        this.optionSelect = orderEntity.getOptionSelect();
    }

    public static List<Order> getList(List<OrderEntity> orderEntities) {
        List<Order> orders = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            Order order = new Order(orderEntity);
            orders.add(order);
        }
        return orders;
    }
}

