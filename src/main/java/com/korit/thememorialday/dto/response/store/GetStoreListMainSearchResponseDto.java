package com.korit.thememorialday.dto.response.store;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.StoreDetail;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import lombok.Getter;

//* stores 페이지에서 가게명 & 상품명 검색으로 가게들 가져오기 위한 dto

@Getter
public class GetStoreListMainSearchResponseDto extends ResponseDto {
  // stores : react responseDTO랑 이름 동일하게
  private List<StoreDetail> storeDetails;

  private GetStoreListMainSearchResponseDto(List<StoreDetail> storeDetails) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.storeDetails = storeDetails;
  }

  public static ResponseEntity<GetStoreListMainSearchResponseDto> success(List<StoreDetail> storeDetails) {
    GetStoreListMainSearchResponseDto responseBody = new GetStoreListMainSearchResponseDto(storeDetails);
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }
}
