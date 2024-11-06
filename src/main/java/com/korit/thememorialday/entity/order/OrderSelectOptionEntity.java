package com.korit.thememorialday.entity.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_select_option")
public class OrderSelectOptionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderSelectNumber;
    
    private String orderCode;
    private Integer optionNumber;
    private Integer optionCategoryNumber;

    public OrderSelectOptionEntity(String orderCode, Integer optionNumber, Integer optionCategoryNumber) {
        this.orderCode = orderCode;
        this.optionNumber = optionNumber;
        this.optionCategoryNumber = optionCategoryNumber;
    }
}
