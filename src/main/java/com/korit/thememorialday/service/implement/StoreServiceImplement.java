package com.korit.thememorialday.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.dto.request.store.PatchStoreRegisterRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreRegisterRequestDto;

import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.auth.GetSignInResponseDto;
import com.korit.thememorialday.entity.ProductEntity;
import com.korit.thememorialday.entity.StoreEntity;
import com.korit.thememorialday.entity.UserEntity;
import com.korit.thememorialday.repository.ProductRepository;
import com.korit.thememorialday.repository.StoreRepository;
import com.korit.thememorialday.service.StoreService;
import com.korit.thememorialday.dto.response.store.GetStoreListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreNumberResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreOrderListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImplement implements StoreService {

  private final StoreRepository storeRepository;
  private final ProductRepository productRepository;

  // 가게 등록
  @Override
  public ResponseEntity<ResponseDto> postStore(PostStoreRegisterRequestDto dto) {

    try {

      StoreEntity storeEntity = new StoreEntity(dto);
      storeRepository.save(storeEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success();
  }

  // 가게수정
  @Override
  public ResponseEntity<ResponseDto> patchStore(Integer storeNumber, PatchStoreRegisterRequestDto dto) {

    try {

      StoreEntity storeEntity = storeRepository.findByStoreNumber(storeNumber);
      if (storeEntity == null)
        return ResponseDto.noExistStore();

      storeEntity.patch(dto);
      storeRepository.save(storeEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }
    return ResponseDto.success();
  }

  @Override
  public ResponseEntity<? super GetStoreResponseDto> getStore(Integer storeNumber) {

    StoreEntity storeEntity = null;

    try {
      storeEntity = storeRepository.findByStoreNumber(storeNumber);
      if (storeNumber == null)
        return ResponseDto.noExistStore();

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetStoreResponseDto.success(storeEntity);
  }

  @Override
  public ResponseEntity<? super GetStoreListResponseDto> getStoreList() {

    List<StoreEntity> storeEntities = new ArrayList<>();

    try {

      storeEntities = storeRepository.findByOrderByStoreNumberDesc();

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetStoreListResponseDto.success(storeEntities);
  }

  @Override
  public ResponseEntity<? super GetStoreOrderListResponseDto> getStoreOrderList(Integer storeNumber) {
    // storeNumber로 ProductEntity 목록 조회 ** 물어보기
    List<ProductEntity> productEntities = productRepository.findByStoreNumber(storeNumber);

    // 응답 DTO 생성
    GetStoreOrderListResponseDto responseDto = new GetStoreOrderListResponseDto(productEntities);

    // ResponseEntity 반환
    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<? super GetStoreResponseDto> getStore(String userId) {

    StoreEntity storeEntity = null;

    try {
      storeEntity = storeRepository.findByUserId(userId);
      if (userId == null)
        return ResponseDto.noExistStore();

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetStoreResponseDto.success(storeEntity);
  }

  public ResponseEntity<? super GetStoreNumberResponseDto> getStoreNumber(String userId) {
    StoreEntity storeEntity = null;
    try {
      storeEntity = storeRepository.findByUserId(userId);
      if (storeEntity == null)
        return ResponseDto.noExistStore();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetStoreNumberResponseDto.success(storeEntity);
  }
}
