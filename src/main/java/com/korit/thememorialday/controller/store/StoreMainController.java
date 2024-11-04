package com.korit.thememorialday.controller.store;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.response.store.GetStoreListMainSearchResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreOrderListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreResponseDto;
import com.korit.thememorialday.service.StoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreMainController {

  private final StoreService storeService;

  @GetMapping(value = { "/", "" })
  public ResponseEntity<? super GetStoreListResponseDto> getStoreList() {
    ResponseEntity<? super GetStoreListResponseDto> response = storeService.getStoreList();
    return response;
  }

  @GetMapping(value = "/{storeNumber}")
  public ResponseEntity<? super GetStoreResponseDto> getStore(
      @PathVariable("storeNumber") Integer storeNumber) {
    ResponseEntity<? super GetStoreResponseDto> reponse = storeService.getStore(storeNumber);
    return reponse;
  }

  @GetMapping(value = { "/{storeNumber}/order" })
  public ResponseEntity<? super GetStoreOrderListResponseDto> getStoreOrderList(
      @PathVariable("storeNumber") Integer storeNumber) {
    ResponseEntity<? super GetStoreOrderListResponseDto> response = storeService.getStoreOrderList(storeNumber);
    return response;
  }

  @GetMapping(value = "/{storeNumber}/information")
  public ResponseEntity<? super GetStoreResponseDto> getStoreInformation(
      @PathVariable("storeNumber") Integer storeNumber) {
    ResponseEntity<? super GetStoreResponseDto> reponse = storeService.getStore(storeNumber);
    return reponse;
  }

  @GetMapping(value = "/{storeNumber}/contact")
  public ResponseEntity<? super GetStoreResponseDto> getStoreContact(
      @PathVariable("storeNumber") Integer storeNumber) {
    ResponseEntity<? super GetStoreResponseDto> reponse = storeService.getStore(storeNumber);
    return reponse;
  }

  @GetMapping(value = "/{storeNumber}/review")
  public ResponseEntity<? super GetStoreResponseDto> getStoreReview(
      @PathVariable("storeNumber") Integer storeNumber) {
    ResponseEntity<? super GetStoreResponseDto> reponse = storeService.getStore(storeNumber);
    return reponse;
  }

  //* store main search - 병합
  @GetMapping(value = "/search-main")
  public ResponseEntity<? super GetStoreListMainSearchResponseDto> getStoreMainSearch(
    @RequestParam(name="storeName", defaultValue="") String storeName,
    @RequestParam(name="productName", defaultValue="") String productName
  ) {

    ResponseEntity<? super GetStoreListMainSearchResponseDto> response = storeService.getStoreMainSearch(storeName, productName);
    return response;

  };

  //* store main filter open day
  @GetMapping(value = "/selected-day")
  public ResponseEntity<? super GetStoreListMainSearchResponseDto> getStoreMainFilterDays() {
    ResponseEntity<? super GetStoreListMainSearchResponseDto> response = storeService.getStoresByOpenDays();
    return response;
  };

}
