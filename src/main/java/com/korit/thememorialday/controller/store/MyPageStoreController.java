package com.korit.thememorialday.controller.store;

import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.store.PatchStoreRegisterRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreRegisterRequestDto;

import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.service.StoreService;
import com.korit.thememorialday.dto.response.store.GetStoreResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/mypage/store")
@RequiredArgsConstructor
public class MyPageStoreController {

  private final StoreService storeService;

  @PostMapping(value = { "/", "" })
  public ResponseEntity<ResponseDto> postStore(
      @RequestBody @Valid PostStoreRegisterRequestDto requestBody) {
    ResponseEntity<ResponseDto> response = storeService.postStore(requestBody);
    return response;
  }

  @PatchMapping(value = "/{storeNumber}")
  public ResponseEntity<ResponseDto> patchStore(
      @PathVariable("storeNumber") Integer storeNumber,
      @RequestBody @Valid PatchStoreRegisterRequestDto requestBody) {
    ResponseEntity<ResponseDto> response = storeService.patchStore(storeNumber, requestBody);
    return response;
  }

  @GetMapping(value = "/{storeNumber}")
  public ResponseEntity<? super GetStoreResponseDto> getStore(
      @PathVariable("storeNumber") Integer storeNumber) {
    ResponseEntity<? super GetStoreResponseDto> reponse = storeService.getStore(storeNumber);
    return reponse;
  }

  @GetMapping(value = { "/", "" })
  public ResponseEntity<? super GetStoreResponseDto> getStoreInfo(
      @RequestParam("userId") String userId) {
    ResponseEntity<? super GetStoreResponseDto> reponse = storeService.getStore(userId);
    return reponse;
  }
}
