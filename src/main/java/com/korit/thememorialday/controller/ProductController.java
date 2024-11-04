package com.korit.thememorialday.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.product.PatchProductRequestDto;
import com.korit.thememorialday.dto.request.product.PostProductRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.product.GetProductListResponseDto;
import com.korit.thememorialday.dto.response.product.GetProductResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreNumberResponseDto;
import com.korit.thememorialday.service.ProductService;
import com.korit.thememorialday.service.StoreService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final StoreService storeService;

    @PostMapping(value = { "/{storeNubmer}" })
    public ResponseEntity<ResponseDto> postProduct(
        @RequestBody @Valid PostProductRequestDto requestBody,
        @PathVariable("storeNubmer") Integer storeNubmer
    ) {
        ResponseEntity<ResponseDto> response = productService.postProduct(requestBody, storeNubmer);
        return response;
    }

    @GetMapping("/{userId}")                                       // 이거 지우고 토큰 인증 로그인까지 완료되면 윗줄 써야 함
    public ResponseEntity<? super GetProductListResponseDto> getProductsByStoreNumber(
        @PathVariable String userId
    ) {
        return productService.getProductList(userId);
    }

    @GetMapping("/add/{userId}")                                       // 이거 지우고 토큰 인증 로그인까지 완료되면 윗줄 써야 함
    public ResponseEntity<? super GetStoreNumberResponseDto> getStoreNumber(
        @PathVariable String userId
    ) {
        return storeService.getStoreNumber(userId);
    }
    
    @GetMapping("/update/{productNumber}")
    public ResponseEntity<? super GetProductResponseDto> getProduct(
        @PathVariable("productNumber") Integer productNumber
    ) {
        ResponseEntity<? super GetProductResponseDto> response = productService.getProduct(productNumber);
        return response;
    }

    @PatchMapping("/{productNumber}")
    public ResponseEntity<ResponseDto> patchProduct(
        @PathVariable("productNumber") Integer productNumber,
        @RequestBody @Valid PatchProductRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = productService.patchProduct(productNumber, requestBody);
        return response;
    }

}
