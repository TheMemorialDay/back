package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.store.PatchStoreRegisterRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreRegisterRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreListMainSearchResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreNumberResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreOrderListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreResponseDto;
import com.korit.thememorialday.dto.response.store.GetPopularKeywordResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreDetailListResponseDto;

public interface StoreService {

  ResponseEntity<ResponseDto> postStore(PostStoreRegisterRequestDto dto);

  ResponseEntity<ResponseDto> patchStore(Integer storeNumber, PatchStoreRegisterRequestDto dto);

  ResponseEntity<? super GetStoreResponseDto> getStore(Integer storeNumber);

  ResponseEntity<? super GetStoreListResponseDto> getStoreList();

  ResponseEntity<? super GetStoreOrderListResponseDto> getStoreOrderList(Integer storeNumber);

  ResponseEntity<? super GetStoreResponseDto> getStore(String userId);

  ResponseEntity<? super GetStoreNumberResponseDto> getStoreNumber(String userId);

  // * stores main search - storeName & productName 으로 검색
  ResponseEntity<? super GetStoreListMainSearchResponseDto> getStoreMainSearch(String searchKeyword);

  ResponseEntity<? super GetStoreDetailListResponseDto> getStoreDetailList();

  //* 인기 검색어 저장 */
  ResponseEntity<ResponseDto> postKeyword(String keyword);
  // //* 인기 검색어 인기순 조회 */
  // ResponseEntity<? super GetPopularKeywordResponseDto> getKeyword();
}
