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

    // private Integer optionNumber;
    private Integer optionCategoryNumber;

    public OrderSelectOption(OrderSelectOptionEntity orderSelectOptionEntity) {

        // this.optionNumber = orderSelectOptionEntity.getOptionNumber();       // optionNumber 안 받기로 해당 줄에서 지웠습니다
        this.optionCategoryNumber = orderSelectOptionEntity.getOptionCategoryNumber();
    }
}
