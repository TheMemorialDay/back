package com.korit.thememorialday.dto.response.store;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.OrderProductDetail;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetProductDetailResponseDto extends ResponseDto{
    
    OrderProductDetail orderProductDetails;
    
    private GetProductDetailResponseDto(OrderProductDetail orderProductDetails) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.orderProductDetails = orderProductDetails;
    }

    public static ResponseEntity<GetProductDetailResponseDto> success(OrderProductDetail orderProductDetails) {
        GetProductDetailResponseDto responseBody = new GetProductDetailResponseDto(orderProductDetails);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
