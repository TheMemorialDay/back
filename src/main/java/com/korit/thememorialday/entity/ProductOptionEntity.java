package com.korit.thememorialday.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_option")
public class ProductOptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer optionCategoryNumber;

    @Column(name = "product_category", nullable = false)
    private String productCategory;

    @Column(name = "product_option_price", nullable = false)
    private int productOptionPrice;

    @ManyToOne
    @JoinColumn(name = "option_number", nullable = false)
    private ProductMappingEntity productMapping;
}
