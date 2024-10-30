package com.korit.thememorialday.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_option")
public class ProductOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer optionCategoryNumber;
    private Integer optionNumber;
    private String productCategory;
    private int productOptionPrice;
    
}