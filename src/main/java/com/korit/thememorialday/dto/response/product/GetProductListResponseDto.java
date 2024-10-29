package com.korit.thememorialday.dto.response.product;

import lombok.Getter;
import lombok.ToString;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class GetProductListResponseDto extends ResponseDto {
    
    private List<GetProductResponseDto> products;

    public GetProductListResponseDto(List<ProductEntity> productEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.products = productEntities.stream()
                .map(GetProductResponseDto::new)
                .collect(Collectors.toList());
    }

    public static ResponseEntity<GetProductListResponseDto> success(List<ProductEntity> productEntities) {
        GetProductListResponseDto responseBody = new GetProductListResponseDto(productEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
