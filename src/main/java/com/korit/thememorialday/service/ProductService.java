package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.product.PostProductRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.product.GetProductListResponseDto;

public interface ProductService {
    ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto);
        ResponseEntity<GetProductListResponseDto> getProductsByUserId(String userId);
    // ResponseEntity<? super GetProductListResponseDto> getProductList();
    // ResponseEntity<? super GetProductResponseDto> getProduct(Integer productNumber);
    // ResponseEntity<ResponseDto> patchProduct(Integer productNumber, PatchProductRequestDto dto);
    // ResponseEntity<ResponseDto> deleteProduct(Integer productNumber);
}

