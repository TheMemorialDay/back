package com.korit.thememorialday.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostProductOptionDetailRequestDto {
    @NotBlank(message = "옵션 종류는 필수입니다.")
    private String productCategory;

    @NotNull(message = "옵션 가격은 필수입니다.")
    private Integer productOptionPrice;
}
