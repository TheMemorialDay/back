// package com.korit.thememorialday.common.object.order;

// import java.util.List;

// import com.korit.thememorialday.entity.order.OrderEntity;

// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// public class FullOrder {
//     private String orderCode;
//     private String orderStatus;
//     private String orderTime;
//     private String storeName;
//     private String productName;
//     private String productContents;
//     private String pickupTime;
//     private Integer totalPrice;
//     private List<OrderSelectOption> options;

//     public FullOrder(OrderEntity order, List<OrderSelectOption> options) {
//         this.orderCode = order.getOrderCode();
//         this.orderStatus = order.getOrderStatus();
//         this.orderTime = order.getOrderTime().toString();  // Adjusted to String if needed
//         this.storeName = order.getStoreName();
//         this.productName = order.getProductName();
//         this.productContents = order.getProductContents();
//         this.pickupTime = order.getPickupTime();
//         this.totalPrice = order.getTotalPrice();
//         this.options = options;  // List of options passed in
//     }
// }



// mypage에서 get 할 때 사용할 예정