package com.korit.thememorialday.dto.response.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class GetProductListResponseDto {
    
    private List<GetProductResponseDto> products;

    public GetProductListResponseDto(List<ProductEntity> productEntities) {
        this.products = productEntities.stream()
                .map(GetProductResponseDto::new)
                .collect(Collectors.toList());
    }

    public static ResponseEntity<GetProductListResponseDto> success(List<ProductEntity> productEntities) {
        GetProductListResponseDto responseBody = new GetProductListResponseDto(productEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
