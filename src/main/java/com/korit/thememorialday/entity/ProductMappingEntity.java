package com.korit.thememorialday.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.korit.thememorialday.dto.request.product.PostProductOptionDetailRequestDto;
import com.korit.thememorialday.dto.request.product.PostProductOptionRequestDto;

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

    public ProductMappingEntity(PostProductOptionRequestDto optionDto, ProductEntity product) {
        this.productOptionName = optionDto.getProductOptionName();
        this.product = product;
        for (PostProductOptionDetailRequestDto detailDto : optionDto.getOptionDetails()) {
            this.optionDetails.add(new ProductOptionEntity(detailDto.getProductCategory(), detailDto.getProductOptionPrice(), this));
        }
    }
}
