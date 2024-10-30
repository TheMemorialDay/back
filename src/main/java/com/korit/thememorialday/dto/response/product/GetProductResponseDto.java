package com.korit.thememorialday.dto.response.product;

import lombok.Getter;
import lombok.ToString;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class GetProductResponseDto extends ResponseDto {
    private Integer productNumber;
    private String productName;
    private String productIntroduce;
    private Integer productPrice;
    private boolean productToday;
    private String productTag;
    private String imageUrl;
    private List<String> productImages; // 이미지 URL 리스트
    private List<GetProductOptionResponseDto> options; // 옵션 리스트
    private List<String> themes; // 테마 리스트

}