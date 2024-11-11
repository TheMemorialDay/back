package com.korit.thememorialday.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.common.object.Store;
import com.korit.thememorialday.common.object.StoreDetail;
import com.korit.thememorialday.dto.request.store.PatchStoreRegisterRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreRegisterRequestDto;

import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.entity.ProductEntity;
import com.korit.thememorialday.entity.ReviewEntity;
import com.korit.thememorialday.entity.StoreEntity;
import com.korit.thememorialday.entity.LikeEntity;
import com.korit.thememorialday.entity.ThemaEntity;
import com.korit.thememorialday.repository.LikeRepository;
import com.korit.thememorialday.repository.ProductRepository;
import com.korit.thememorialday.repository.ReviewRepository;
import com.korit.thememorialday.repository.StoreRepository;
import com.korit.thememorialday.repository.ThemaRepostiroy;
import com.korit.thememorialday.service.StoreService;
import com.korit.thememorialday.dto.response.store.GetStoreListMainSearchResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreDetailListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreNumberResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreOrderListResponseDto;
import com.korit.thememorialday.dto.response.store.GetStoreResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImplement implements StoreService {

  private final LikeRepository likeRepository;
  private final StoreRepository storeRepository;
  private final ProductRepository productRepository;
  private final ThemaRepostiroy themaRepostiory;
  private final ReviewRepository reviewRepository;

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
    Double reviewRating = 0.0;

    try {
      storeEntity = storeRepository.findByStoreNumber(storeNumber);
      if (storeNumber == null)
        return ResponseDto.noExistStore();

        List<ReviewEntity> reviewEntities = reviewRepository.findByStoreName(storeEntity.getStoreName());
        Integer sum = 0;
        
        if(reviewEntities.size() != 0) {
          for(ReviewEntity reviewEntity: reviewEntities) sum = sum + reviewEntity.getReviewRating();
          reviewRating =  (sum / (double)reviewEntities.size());
        }

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetStoreResponseDto.success(storeEntity, reviewRating);
  }

  @Override
  public ResponseEntity<? super GetStoreListResponseDto> getStoreList() {

    List<Store> stores = new ArrayList<>();

    try {

      List<StoreEntity> storeEntities = storeRepository.findByOrderByStoreNumberDesc();
      for (StoreEntity storeEntity : storeEntities) {
        Integer storeNumber = storeEntity.getStoreNumber();

        List<LikeEntity> likeEntities = likeRepository.findByStoreNumber(storeNumber);
        //List<ReviewEntity> reviewEntities = reviewRepository.findByStoreName(storeEntity.getStoreName());

        Store store = new Store(storeEntity, likeEntities);
        stores.add(store);
      }

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetStoreListResponseDto.success(stores);
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
    Double reviewRating = 0.0;

    try {
      storeEntity = storeRepository.findByUserId(userId);
      if (userId == null) return ResponseDto.noExistStore();

        List<ReviewEntity> reviewEntities = reviewRepository.findByStoreName(storeEntity.getStoreName());
        Integer sum = 0;
        
        if(reviewEntities.size() != 0) {
          for(ReviewEntity reviewEntity: reviewEntities) sum = sum + reviewEntity.getReviewRating();
          reviewRating =  (sum / (double)reviewEntities.size());
        }  

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetStoreResponseDto.success(storeEntity, reviewRating);
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

  //* store main search - storeName & productName 으로 검색
  @Override
  public ResponseEntity<? super GetStoreListMainSearchResponseDto> getStoreMainSearch(
    String storeName, String productName) {

    List<StoreEntity> storeEntities = new ArrayList<>();

    try {

      storeEntities = storeRepository.getStoreByMainSearch(storeName, productName);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetStoreListMainSearchResponseDto.success(storeEntities);

  }

  
  @Override
  public ResponseEntity<? super GetStoreDetailListResponseDto> getStoreDetailList() {

    List<StoreDetail> stores = new ArrayList<>();

    try {
      List<StoreEntity> storeEntities = storeRepository.findByOrderByStoreNumberDesc();

      for (StoreEntity storeEntity : storeEntities) {
        Integer storeNumber = storeEntity.getStoreNumber();
        List<LikeEntity> likeEntities = likeRepository.findByStoreNumber(storeNumber);
        List<ProductEntity> productEntities = productRepository.findByStoreNumber(storeNumber);

        Integer reviewCount = reviewRepository.countByStoreName(storeEntity.getStoreName());
        
        List<ReviewEntity> reviewEntities = reviewRepository.findByStoreName(storeEntity.getStoreName());
        Integer sum = 0;
        Double reviewRating = 0.0;

        if(reviewEntities.size() != 0) {
          for(ReviewEntity reviewEntity: reviewEntities) sum = sum + reviewEntity.getReviewRating();
          reviewRating =  (sum / (double)reviewEntities.size());
        }else reviewRating = 0.0;
        
        // productEntities의 각 productNumber에 대해 ThemaEntity를 조회
        List<ThemaEntity> themaEntities = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
          List<ThemaEntity> themasForProduct = themaRepostiory.findByProductNumber(productEntity.getProductNumber());
          themaEntities.addAll(themasForProduct);
        }
        StoreDetail store = new StoreDetail(storeEntity, likeEntities, productEntities, themaEntities, 
        reviewCount, reviewRating);
        stores.add(store);
      }

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetStoreDetailListResponseDto.success(stores);
  }

}