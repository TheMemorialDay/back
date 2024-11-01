package com.korit.thememorialday.dto.response.store;
import java.util.List;
import lombok.Getter;

@Getter
public class GetProductListResponseDto {
    private Integer productNumber;
    private String productName;
    private Integer productPrice;
    private List<String> themes; // 테마 리스트
    private List<String> productImages; // 이미지 URL 리스트
}
