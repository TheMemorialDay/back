package com.korit.thememorialday.controller.order;

// import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.order.PatchOrderStatusDto;
import com.korit.thememorialday.dto.request.order.PostOrderRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.order.GetOrderListResponseDto;
import com.korit.thememorialday.service.order.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderService orderService;

    @PostMapping(value = { "/stores/{storeNumber}/order/{productNumber}/{userId}" })
    public ResponseEntity<ResponseDto> postOrder(
            @RequestBody @Valid PostOrderRequestDto requestBody,
            @PathVariable("storeNumber") Integer storeNumber,
            @PathVariable("productNumber") Integer productNumber,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = orderService.postOrder(requestBody, userId, storeNumber, productNumber);
        return response;
    }

    @PatchMapping(value = "/mypage/order-detail/{orderCode}")
    public ResponseEntity<ResponseDto> patchOrderStatus(
            @PathVariable("orderCode") String orderCode,
            @RequestBody @Valid PatchOrderStatusDto requestBody) {
        ResponseEntity<ResponseDto> response = orderService.patchOrderStatus(orderCode, requestBody);
        return response;
    }

    @GetMapping("/mypage/order-detail/{userId}")
    public ResponseEntity<GetOrderListResponseDto> getOrderDetail(@PathVariable("userId") String userId) {
        return orderService.getOrderList(userId);
    }

    @GetMapping("/mypage/order-manage/{storeNumber}")
    public ResponseEntity<GetOrderListResponseDto> getOrderManageDetail(
            @PathVariable("storeNumber") Integer storeNumber) {
        return orderService.getOrderManageList(storeNumber);
    }
}
