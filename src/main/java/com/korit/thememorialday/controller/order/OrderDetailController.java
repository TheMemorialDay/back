package com.korit.thememorialday.controller.order;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.order.PatchOrderStatusDto;
import com.korit.thememorialday.dto.request.order.PostOrderRequestDto;
import com.korit.thememorialday.dto.request.order.PostSendPaymentMsgRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.order.GetOrderListResponseDto;
import com.korit.thememorialday.dto.response.order.GetOrderManageListResponseDto;
import com.korit.thememorialday.dto.response.sales.GetSalesResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreNumberResponseDto;
import com.korit.thememorialday.service.order.OrderService;
import com.korit.thememorialday.service.StoreService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderService orderService;
    private final StoreService storeService;

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
    public ResponseEntity<? super GetOrderManageListResponseDto> getOrderDetail(@PathVariable("userId") String userId) {
        return orderService.getOrderList(userId);
    }

    @GetMapping("/mypage/order-manage/{storeNumber}")
    public ResponseEntity<? super GetOrderManageListResponseDto> getOrderManageDetail(
            @PathVariable("storeNumber") Integer storeNumber) {
        return orderService.getOrderManageListUser(storeNumber);
    }

    @GetMapping("/mypage/sales")
    public ResponseEntity<GetSalesResponseDto> getSales(@RequestParam String userId) {

        ResponseEntity<? super GetStoreNumberResponseDto> storeNumberResponse = storeService.getStoreNumber(userId);

        // storeNumber가 없으면 404 반환
        // if (storeNumberResponse.getStatusCode() != HttpStatus.OK ||
        // storeNumberResponse.getBody() == null) {
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        // }

        // storeNumber 추출
        GetStoreNumberResponseDto storeNumberDto = (GetStoreNumberResponseDto) storeNumberResponse.getBody();
        Integer storeNumber = storeNumberDto.getStoreNumber();

        // 매출 데이터 반환
        return orderService.getSales(storeNumber);
    }

    @PostMapping("/mypage/order-manage/send-pay-msg")
    public ResponseEntity<ResponseDto> sendPayMsg(
            @RequestBody @Valid PostSendPaymentMsgRequestDto requestBody,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<ResponseDto> response = orderService.postSendPaymentMsg(requestBody);
        return response;
    }
}
