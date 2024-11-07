package com.korit.thememorialday.dto.response.store;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.StoreDetail;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetStoreDetailListResponseDto extends ResponseDto {

  private List<StoreDetail> storeDetails;

  private GetStoreDetailListResponseDto(List<StoreDetail> storeDetails) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.storeDetails = storeDetails;
  }

  public static ResponseEntity<GetStoreDetailListResponseDto> success(List<StoreDetail> storeDetails) {
    GetStoreDetailListResponseDto responseBody = new GetStoreDetailListResponseDto(storeDetails);
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }
}
