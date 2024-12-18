package com.korit.thememorialday.common.object;

import com.korit.thememorialday.entity.ProductOptionEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptionDetail {
    @NotBlank(message = "옵션 종류는 필수입니다.")
    private String productCategory;

    @NotNull(message = "옵션 가격은 필수입니다.")
    private Integer productOptionPrice;

    @NotNull(message = "옵션 카테고리 번호")
    private Integer optionCategoryNumber;

    public OptionDetail(ProductOptionEntity productOptionEntity) {
        this.optionCategoryNumber = productOptionEntity.getOptionCategoryNumber();
        this.productCategory = productOptionEntity.getProductCategory();
        this.productOptionPrice = productOptionEntity.getProductOptionPrice();
    }
}
