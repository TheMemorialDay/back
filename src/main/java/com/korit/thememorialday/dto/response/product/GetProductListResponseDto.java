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

}
