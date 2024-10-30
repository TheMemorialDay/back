package com.korit.thememorialday.dto.response.product;

// import lombok.Getter;
// import lombok.ToString;
import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// import com.korit.thememorialday.dto.response.ResponseCode;
// import com.korit.thememorialday.dto.response.ResponseDto;
// import com.korit.thememorialday.dto.response.ResponseMessage;
// import com.korit.thememorialday.entity.ProductEntity;

// import java.util.List;
// import java.util.stream.Collectors;

// @Getter
// @ToString
// public class GetProductResponseDto extends ResponseDto {
//     private Integer productNumber;
//     private String productName;
//     private String productIntroduce;
//     private Integer productPrice;
//     private boolean productToday;
//     private String productTag;
//     private String imageUrl;
//     private List<String> productImages; // 이미지 URL 리스트
//     private List<GetProductOptionResponseDto> options; // 옵션 리스트
//     private List<String> themes; // 테마 리스트

// }
import com.korit.thememorialday.common.object.Option;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.ProductEntity;

import java.util.List;

@Getter
public class GetProductResponseDto extends ResponseDto {
    private Integer productNumber;
    private String productName;
    private String productIntroduce;
    private Integer productPrice;
    private boolean productToday;
    private String productTag;
    private String imageUrl;
    private List<String> productImages; // 이미지 URL 리스트
    private List<Option> options; // 옵션 리스트
    private List<String> themes; // 테마 리스트

    private GetProductResponseDto(ProductEntity productEntity, List<String> productImages, List<String> themes, List<Option> options) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        String imageUrl = productImages.size() != 0 ? productImages.get(0) : null;
        this.productNumber = productEntity.getProductNumber();
        this.productName = productEntity.getProductName();
        this.productIntroduce = productEntity.getProductIntroduce();
        this.productPrice = productEntity.getProductPrice();
        this.productToday = productEntity.isProductToday();
        this.productTag = productEntity.getProductTag();
        this.imageUrl = imageUrl;
        this.productImages = productImages;
        this.options = options;
        this.themes = themes;
    }

    static public ResponseEntity<GetProductResponseDto> success(ProductEntity productEntity, List<String> productImages, List<String> themes, List<Option> options) {
        GetProductResponseDto responseBody = new GetProductResponseDto(productEntity, productImages, themes, options);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
