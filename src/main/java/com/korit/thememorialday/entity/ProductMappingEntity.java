package com.korit.thememorialday.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_mapping")
public class ProductMappingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer optionNumber;

    @Column(name = "product_option_name", nullable = false)
    private String productOptionName;

    @ManyToOne
    @JoinColumn(name = "product_number", nullable = false)
    private ProductEntity product;

    @OneToMany(mappedBy = "productMapping", cascade = CascadeType.ALL)
    private List<ProductOptionEntity> optionDetails = new ArrayList<>();
}
