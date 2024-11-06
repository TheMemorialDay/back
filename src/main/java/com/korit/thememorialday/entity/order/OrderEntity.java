package com.korit.thememorialday.entity.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.korit.thememorialday.dto.request.order.PostOrderRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`order`")
public class OrderEntity {

    @Id
    @Column(name = "order_code", length = 13)
    private String orderCode;

    private Integer productNumber;
    private Integer storeNumber;
    private String userId;
    private String productContents; // 요청사항
    private String pickupTime;
    private String orderStatus;
    private Integer productCount;
    private Integer totalPrice;
    private LocalDateTime orderTime;
    private String cancelCode;
    private String cancelReason;
    private String optionSelect;

    public OrderEntity(PostOrderRequestDto dto, String userId, Integer storeNumber, Integer productNumber) {
        this.orderCode = generateOrderCode();
        this.productNumber = productNumber;
        this.storeNumber = storeNumber;
        this.userId = userId;
        this.productContents = dto.getProductContents();
        this.pickupTime = dto.getPickupTime();
        this.orderStatus = "승인대기중";
        this.productCount = dto.getProductCount();
        this.totalPrice = dto.getTotalPrice();
        this.orderTime = LocalDateTime.now();
        this.optionSelect = optionSelect;
    }

    private String generateOrderCode() {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        System.out.println(datePart + String.format("%05d", randomNumber));
        return datePart + String.format("%05d", randomNumber);
    }

}