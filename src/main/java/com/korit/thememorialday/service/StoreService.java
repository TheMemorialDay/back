package com.korit.thememorialday.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.store.PatchStoreRegisterRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreByProductMainSearchRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreMainSearchRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreRegisterRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreListMainSearchResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreNumberResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreOrderListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreResponseDto;

public interface StoreService {

  ResponseEntity<ResponseDto> postStore(PostStoreRegisterRequestDto dto);

  ResponseEntity<ResponseDto> patchStore(Integer storeNumber, PatchStoreRegisterRequestDto dto);

  ResponseEntity<? super GetStoreResponseDto> getStore(Integer storeNumber);

  ResponseEntity<? super GetStoreListResponseDto> getStoreList();

  ResponseEntity<? super GetStoreOrderListResponseDto> getStoreOrderList(Integer storeNumber);

  ResponseEntity<? super GetStoreResponseDto> getStore(String userId);

  ResponseEntity<? super GetStoreNumberResponseDto> getStoreNumber(String userId);

  //* stores main page - 가게명으로 가게 검색
  ResponseEntity<? super GetStoreListMainSearchResponseDto> getStoreMainSearchList(
      PostStoreMainSearchRequestDto dto
    );
  //* stores main search - 상품명으로 가게 검색
  ResponseEntity<? super GetStoreListMainSearchResponseDto> getStoreByProductNameMainSearch(
      PostStoreByProductMainSearchRequestDto dto
    );

  //* store main pickup filter - 픽업가능요일 필터링
  ResponseEntity<? super GetStoreListMainSearchResponseDto> getStoresByAvailableDays(List<String> days);
}
