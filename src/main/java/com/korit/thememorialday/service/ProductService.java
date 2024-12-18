package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.product.PatchProductRequestDto;
import com.korit.thememorialday.dto.request.product.PostProductRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.product.GetProductListResponseDto;
import com.korit.thememorialday.dto.response.product.GetProductResponseDto;
import com.korit.thememorialday.dto.response.store.GetProductDetailResponseDto;
import com.korit.thememorialday.dto.response.store.GetProductPreviewListResponseDto;


public interface ProductService {
    ResponseEntity<ResponseDto> postProduct(PostProductRequestDto dto, Integer storeNubmer);
    ResponseEntity<? super GetProductListResponseDto> getProductList(String userId);
    ResponseEntity<? super GetProductResponseDto> getProduct(Integer productNumber);
    ResponseEntity<ResponseDto> patchProduct(Integer productNumber, PatchProductRequestDto dto);

    ResponseEntity<ResponseDto> deleteProduct(Integer productNumber);

    ResponseEntity<? super GetProductPreviewListResponseDto> getProductPreviewList(Integer storeNumber);
    ResponseEntity<? super GetProductDetailResponseDto> getOrderProductDetail(Integer productNumber, Integer storeNumber);
}

