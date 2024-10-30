package com.korit.thememorialday.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_mapping")
public class ProductMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer optionNumber;
    private Integer productNubmer;
    private String productOptionName;
    
}
