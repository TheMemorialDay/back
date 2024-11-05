package com.korit.thememorialday.service.order;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.order.PostOrderRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;

public interface OrderService {
    ResponseEntity<ResponseDto> postOrder(PostOrderRequestDto dto, String userId, Integer storeNumber, Integer productNumber);
    // ResponseEntity<GetOrderResponseDto> getOrderList(String userId);
    ResponseEntity<ResponseDto> cancelOrder(String orderCode);
}
