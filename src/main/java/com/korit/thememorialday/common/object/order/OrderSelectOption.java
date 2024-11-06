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

    private Integer optionNumber;
    private Integer orderCategoryNumber;

    public OrderSelectOption(OrderSelectOptionEntity orderSelectOptionEntity) {

        this.optionNumber = orderSelectOptionEntity.getOptionNumber();
        this.orderCategoryNumber = orderSelectOptionEntity.getOptionCategoryNumber();
    }
}
