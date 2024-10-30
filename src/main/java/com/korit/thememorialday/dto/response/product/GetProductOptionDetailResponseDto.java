package com.korit.thememorialday.dto.response.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.entity.ProductOptionEntity;

@Getter
@Setter
@NoArgsConstructor
public class GetProductOptionDetailResponseDto extends ResponseDto {
    private String productCategory;
    private Integer productOptionPrice;
}
