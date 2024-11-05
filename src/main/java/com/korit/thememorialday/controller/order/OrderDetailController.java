package com.korit.thememorialday.controller.order;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.order.PostOrderRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.service.order.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stores/{storeNumber}/order/{productNumber}")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderService orderService;

    @PostMapping(value = { "/{userId}"})
    public ResponseEntity<ResponseDto> postOrder(
        @RequestBody @Valid PostOrderRequestDto requestBody,
        @PathVariable("storeNumber") Integer storeNumber,
        @PathVariable("productNumber") Integer productNumber,
		@AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = orderService.postOrder(requestBody, userId, storeNumber, productNumber);
        return response;
    }
}
