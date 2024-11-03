package com.korit.thememorialday.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;
import com.korit.thememorialday.dto.request.store.PatchStoreRegisterRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreByProductMainSearchRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreMainSearchRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreRegisterRequestDto;

import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.entity.ProductEntity;
import com.korit.thememorialday.entity.StoreEntity;
import com.korit.thememorialday.repository.ProductRepository;
import com.korit.thememorialday.repository.StoreRepository;
import com.korit.thememorialday.service.StoreService;
import com.korit.thememorialday.dto.response.store.GetStoreListMainSearchResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreNumberResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreOrderListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreResponseDto;

import lombok.RequiredArgsConstructor;

// import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
// import com.querydsl.jpa.impl.JPAQueryFactory;
// import com.korit.thememorialday.QStoreEntity;
// import com.yourproject.entity.StoreEntity;


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

  //* store main search - 가게명 검색 가게리스트 보기
  @Override
  public ResponseEntity<? super GetStoreListMainSearchResponseDto> getStoreMainSearchList(PostStoreMainSearchRequestDto dto) {
    
    List<StoreEntity> storeEntities = new ArrayList<>();

    try {

      storeEntities = storeRepository.findByStoreNameContaining(dto.getStoreName());

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetStoreListMainSearchResponseDto.success(storeEntities);

  }

  //* store main search - 상품명으로 검색 가게리스트 보기
  @Override
  public ResponseEntity<? super GetStoreListMainSearchResponseDto> getStoreByProductNameMainSearch(
      PostStoreByProductMainSearchRequestDto dto) {
    
        List<StoreEntity> storeEntities = new ArrayList<>();

    try {

      // 상품명으로 상품 검색 후, 각 상품에 연결된 가게를 가져와 중복 제거
      storeEntities = productRepository.findByProductNameContaining(dto.getProductName())
                                        .stream()
                                        .map(ProductEntity::getStore) // ProductEntity에서 StoreEntity 추출
                                        .distinct()
                                        .collect(Collectors.toList());

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetStoreListMainSearchResponseDto.success(storeEntities);

  }

  //* store main filter - pickup days select - 가게리스트 보기
  @Override
  public ResponseEntity<? super GetStoreListMainSearchResponseDto> getStoresByAvailableDays(List<String> days) {
    BooleanBuilder builder = new BooleanBuilder();
    return null;
  }
}
