package com.korit.thememorialday.dto.request.order;

import java.util.List;

import com.korit.thememorialday.common.object.order.OrderSelectOption;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostOrderRequestDto {

    @NotBlank(message = "픽업 일시 선택은 필수입니다.")
    private String pickupTime;

    private Integer productCount;

    private String productContents;
    
    private List<OrderSelectOption> options;

    private Integer totalPrice;

    private String photoUrl;
}
