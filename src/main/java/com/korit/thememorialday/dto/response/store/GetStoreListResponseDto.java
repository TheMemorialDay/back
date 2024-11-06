package com.korit.thememorialday.dto.response.store;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.Store;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetStoreListResponseDto extends ResponseDto {

  private List<Store> stores;

  private GetStoreListResponseDto(List<Store> stores) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.stores = stores;
  }

  public static ResponseEntity<GetStoreListResponseDto> success(List<Store> stores) {
    GetStoreListResponseDto responseBody = new GetStoreListResponseDto(stores);
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }

}
