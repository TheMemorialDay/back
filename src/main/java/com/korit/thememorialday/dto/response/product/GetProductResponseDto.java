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

    public GetProductResponseDto(ProductEntity productEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.productNumber = productEntity.getProductNumber();
        this.productName = productEntity.getProductName();
        this.productIntroduce = productEntity.getProductIntroduce();
        this.productPrice = productEntity.getProductPrice();
        this.productToday = productEntity.isProductToday();
        this.productTag = productEntity.getProductTag();
        this.imageUrl = productEntity.getImages().isEmpty() ? null : productEntity.getImages().get(0).getProductImageUrl();
        this.productImages = productEntity.getImages().stream()
                .map(image -> image.getProductImageUrl())
                .collect(Collectors.toList());
        this.options = productEntity.getOptions().stream()
                .map(GetProductOptionResponseDto::new)
                .collect(Collectors.toList());
        this.themes = productEntity.getThemes().stream()
                .map(thema -> thema.getThema())
                .collect(Collectors.toList());
    }

    public static ResponseEntity<GetProductResponseDto> success(ProductEntity productEntity) {
        GetProductResponseDto responseBody = new GetProductResponseDto(productEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
