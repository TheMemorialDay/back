package com.korit.thememorialday.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.product.PostProductRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productservice;

    
    @PostMapping(value = {"", "/"})
    public ResponseEntity<ResponseDto> postProduct(
        @RequestBody @Valid PostProductRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = productservice.postProduct(requestBody);
        return response;
    }
}

