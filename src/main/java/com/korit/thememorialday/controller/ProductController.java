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
import com.korit.thememorialday.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = { "/{storeNubmer}" })
    public ResponseEntity<ResponseDto> postProduct(
        @RequestBody @Valid PostProductRequestDto requestBody,
        @PathVariable("storeNubmer") Integer storeNubmer
    ) {
        ResponseEntity<ResponseDto> response = productService.postProduct(requestBody, storeNubmer);
        return response;
    }


    // @GetMapping("/{userId}")                                             // 원래 이거 써야 함 10/27
    // public ResponseEntity<GetProductListResponseDto> getProductsByStoreNumber(@AuthenticationPrincipal Principal principal, @PathVariable String userId) {
    //     String userId = principal.getUserId(); // 현재 로그인한 유저의 ID 가져오기
    //     return productService.getProductList(userId);
    // }


    @GetMapping("/{userId}")                                       // 이거 지우고 토큰 인증 로그인까지 완료되면 윗줄 써야 함
    public ResponseEntity<? super GetProductListResponseDto> getProductsByStoreNumber(@PathVariable("userId") String userId) {
        return productService.getProductList(userId);
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

    // @GetMapping("/")
    // public ResponseEntity<GetProductListResponseDto> getProductsByStoreNumber(
    //         @AuthenticationPrincipal Principal principal) {
    //     Integer storeNumber = getStoreNumberByUserId(principal.getName());
    //     List<ProductEntity> products = productService.getProductsByStoreNumber(storeNumber);
    //     return GetProductListResponseDto.success(products);
    // }


}
