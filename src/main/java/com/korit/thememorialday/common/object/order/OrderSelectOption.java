package com.korit.thememorialday.common.object.order;

import com.korit.thememorialday.entity.order.OrderSelectOptionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSelectOption {
    private Integer optionCategoryNumber;
    private Integer optionNumber;
    private String productCategory;  // productCategory 필드 추가

    public OrderSelectOption(OrderSelectOptionEntity orderSelectOptionEntity, String productCategory) {
        this.optionCategoryNumber = orderSelectOptionEntity.getOptionCategoryNumber();
        this.optionNumber = orderSelectOptionEntity.getOptionNumber();
        this.productCategory = productCategory;  // 초기화
    }
}
