package com.korit.thememorialday.dto.response.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.korit.thememorialday.entity.ProductEntity;

@Getter
@NoArgsConstructor
public class GetProductResponseDto {
    private Integer productNumber;
    private String productName;
    private String productIntroduce;
    private Integer productPrice;
    private String imageUrl;

    public GetProductResponseDto(ProductEntity productEntity) {
        this.productNumber = productEntity.getProductNumber();
        this.productName = productEntity.getProductName();
        this.productIntroduce = productEntity.getProductIntroduce();
        this.productPrice = productEntity.getProductPrice();
        this.imageUrl = productEntity.getImages().isEmpty() ? null : productEntity.getImages().get(0).getProductImageUrl();
    }

    public static ResponseEntity<GetProductResponseDto> success(ProductEntity productEntity) {
        GetProductResponseDto responseBody = new GetProductResponseDto(productEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
