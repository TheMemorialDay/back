package com.korit.thememorialday.service.order;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.order.PostOrderRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.order.GetOrderListResponseDto;
import com.korit.thememorialday.dto.response.order.GetOrderResponseDto;

public interface OrderService {
    ResponseEntity<ResponseDto> postOrder(PostOrderRequestDto dto, String userId, Integer storeNumber, Integer productNumber);
    ResponseEntity<GetOrderListResponseDto> getOrderList(String userId);
    ResponseEntity<ResponseDto> cancelOrder(String orderCode);
}
