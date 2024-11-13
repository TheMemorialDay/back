package com.korit.thememorialday.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostSendPaymentMsgRequestDto {
    private String telNumber;
    private String name;
    private String storeName;
    private String productName;
    private Integer totalPrice;
}
