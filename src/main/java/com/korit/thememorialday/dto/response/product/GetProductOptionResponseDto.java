package com.korit.thememorialday.dto.response.product;


import com.korit.thememorialday.entity.ProductMappingEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GetProductOptionResponseDto {
    private String productOptionName;
    private List<GetProductOptionDetailResponseDto> optionDetails;

    public GetProductOptionResponseDto(ProductMappingEntity productMappingEntity) {
        this.productOptionName = productMappingEntity.getProductOptionName();
        this.optionDetails = productMappingEntity.getOptionDetails().stream()
                .map(GetProductOptionDetailResponseDto::new)
                .collect(Collectors.toList());
    }
}
