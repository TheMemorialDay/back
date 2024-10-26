package com.korit.thememorialday.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.product.PostProductRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.product.GetProductListResponseDto;
import com.korit.thememorialday.dto.response.product.GetProductResponseDto;
import com.korit.thememorialday.entity.ProductEntity;
import com.korit.thememorialday.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = { "/add" })
    public ResponseEntity<ResponseDto> postProduct(
            @RequestBody @Valid PostProductRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = productService.postProduct(requestBody);
        return response;
    }


    @GetMapping("/")
    public ResponseEntity<GetProductListResponseDto> getProductsByStoreNumber(@AuthenticationPrincipal Principal principal) {
        String userId = principal.getName(); // 현재 로그인한 유저의 ID 가져오기
        return productService.getProductsByUserId(userId); // 유저 ID를 기반으로 상품 목록 조회
    }

    // @GetMapping("/")
    // public ResponseEntity<GetProductListResponseDto> getProductsByStoreNumber(
    //         @AuthenticationPrincipal Principal principal) {
    //     Integer storeNumber = getStoreNumberByUserId(principal.getName());
    //     List<ProductEntity> products = productService.getProductsByStoreNumber(storeNumber);
    //     return GetProductListResponseDto.success(products);
    // }

    // @GetMapping("/{productNumber}")
    // public ResponseEntity<GetProductResponseDto> getProduct(@PathVariable Integer
    // productNumber) {
    // ProductEntity product = productService.getProductByNumber(productNumber);
    // return GetProductResponseDto.success(product);
    // }

}
