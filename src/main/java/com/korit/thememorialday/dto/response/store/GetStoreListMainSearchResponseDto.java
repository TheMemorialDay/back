package com.korit.thememorialday.dto.response.store;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.stores_main_search.StoresMainSearch;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.StoreEntity;

import lombok.Getter;

//* stores 페이지에서 가게명 & 상품명 검색으로 가게들 가져오기 위한 dto

@Getter
public class GetStoreListMainSearchResponseDto extends ResponseDto {
	// stores : react responseDTO랑 이름 동일하게
	private List<StoresMainSearch> stores;

	private GetStoreListMainSearchResponseDto(List<StoreEntity> storeEntities) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.stores = StoresMainSearch.getList(storeEntities);
	}

	public static ResponseEntity<GetStoreListMainSearchResponseDto> success(List<StoreEntity> storeEntities) {
		GetStoreListMainSearchResponseDto responseBody = new GetStoreListMainSearchResponseDto(storeEntities);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}
}
