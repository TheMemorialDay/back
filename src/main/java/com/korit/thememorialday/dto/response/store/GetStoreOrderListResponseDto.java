package com.korit.thememorialday.dto.response.store;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.Product;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.ProductEntity;

import lombok.Getter;

@Getter
public class GetStoreOrderListResponseDto extends ResponseDto {

  private List<Product> product;

  public GetStoreOrderListResponseDto(List<ProductEntity> productEntities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.product = Product.getList(productEntities);
  }

  public static ResponseEntity<GetStoreOrderListResponseDto> success(List<ProductEntity> productEntities) {
    GetStoreOrderListResponseDto responseBody = new GetStoreOrderListResponseDto(productEntities);
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }

}
