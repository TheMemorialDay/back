package com.korit.thememorialday.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchProductRequestDto {
    @NotBlank(message = "상품 이름은 필수입니다.")
    private String productName;

    private String productIntroduce;

    @NotNull(message = "상품 가격은 필수입니다.")
    private Integer productPrice;

    private boolean productToday;

    @NotBlank(message = "상품 태그는 필수입니다.")
    private String productTag;

    @NotNull(message = "옵션 리스트는 비어있을 수 없습니다.")
    private List<PostProductOptionRequestDto> options;

    @NotNull(message = "이미지 URL 리스트는 비어있을 수 없습니다.")
    private List<String> productImages;

    private List<String> themes;
}