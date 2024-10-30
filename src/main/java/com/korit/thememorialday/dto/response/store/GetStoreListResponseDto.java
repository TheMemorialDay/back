package com.korit.thememorialday.dto.response.store;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.Store;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.StoreEntity;

import lombok.Getter;

@Getter
public class GetStoreListResponseDto extends ResponseDto {

  private List<Store> stores;

  private GetStoreListResponseDto(List<StoreEntity> storeEntities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.stores = Store.getList(storeEntities);
  }

  public static ResponseEntity<GetStoreListResponseDto> success(List<StoreEntity> storeEntities) {
    GetStoreListResponseDto responseBody = new GetStoreListResponseDto(storeEntities);
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }

}
