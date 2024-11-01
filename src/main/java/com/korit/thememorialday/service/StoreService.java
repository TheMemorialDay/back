package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.store.PatchStoreRegisterRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreRegisterRequestDto;

import com.korit.thememorialday.dto.response.ResponseDto;
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

}
