package com.korit.thememorialday.dto.response.order;

import java.util.List;

import com.korit.thememorialday.common.object.order.FullOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetOrderResponseDto {
    private List<FullOrder> orders;
}