package com.korit.thememorialday.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.common.object.LikeStoreReviewNRating;
import com.korit.thememorialday.dto.request.like.PostLikeStoreRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.like.GetLikeStoreListResponseDto;
import com.korit.thememorialday.dto.response.like.GetLikedStoreResponseDto;
import com.korit.thememorialday.dto.response.like.GetLikedStoreReviewRatingResponseDto;
import com.korit.thememorialday.dto.response.like.GetUserLikeListResponseDto;
import com.korit.thememorialday.entity.LikeEntity;
import com.korit.thememorialday.entity.ReviewEntity;
import com.korit.thememorialday.entity.StoreEntity;
import com.korit.thememorialday.repository.LikeRepository;
import com.korit.thememorialday.repository.ReviewRepository;
import com.korit.thememorialday.repository.StoreRepository;
import com.korit.thememorialday.repository.resultSet.GetLikeUserResultSet;
import com.korit.thememorialday.service.LikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImplement implements LikeService {

  private final LikeRepository likeRepository;
  private final StoreRepository storeRepository;
  private final ReviewRepository reviewRepository;

  @Override
  public ResponseEntity<ResponseDto> postLike(PostLikeStoreRequestDto dto) {

    try {

      LikeEntity likeEntity = new LikeEntity(dto);
      likeRepository.save(likeEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success();
  }

  @Override
  public ResponseEntity<ResponseDto> deleteLike(String userId, Integer storeNumber) {
    try {

      LikeEntity likeEntity = likeRepository.findByUserIdAndStoreNumber(userId, storeNumber);
      if (likeEntity == null)
        return ResponseDto.noExistStore();

      likeRepository.delete(likeEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success();
  }

  @Override
  public ResponseEntity<? super GetUserLikeListResponseDto> getUserLikeList(String userId) {

    List<GetLikeUserResultSet> resultSets = new ArrayList<>();

    try {

      resultSets = likeRepository.getLikeStoreList(userId);
      if (resultSets == null) {
        return ResponseDto.noExistStore();
      }

      // List<LikeEntity> likeEntities = likeRepository.findByUserId(userId);
      // for(LikeEntity likeEntity: likeEntities) {
      //   Integer storeNumber = likeEntity.getStoreNumber();
      //   StoreEntity storeEntity = storeRepository.findByStoreNumber(storeNumber);
        
      //   String storeName = storeEntity.getStoreName();
      //   List<ReviewEntity> reviewEntities = reviewRepository.findByStoreName(storeName);
      //   Integer reviewCount = reviewEntities.size();
  

      //   Integer sum = 0;
      //   Double reviewRating = 0.0;
      //   if(reviewEntities.size() != 0) {
      //     for(ReviewEntity reviewEntity: reviewEntities) sum = sum + reviewEntity.getReviewRating();
      //     reviewRating =  (sum / (double)reviewEntities.size());
      //   }
      // }

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetUserLikeListResponseDto.success(resultSets);
  }

  @Override
  public ResponseEntity<? super GetLikeStoreListResponseDto> getStoreNumberList(String userId) {

    List<LikeEntity> likeEntities = new ArrayList<>();
    try {

      likeEntities = likeRepository.findByUserId(userId);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetLikeStoreListResponseDto.success(likeEntities);
  }

  @Override
  public ResponseEntity<? super GetLikedStoreResponseDto> getStoreLike(Integer storeNumber) {

    List<LikeEntity> likeEntities = new ArrayList<>();

    try {

      likeEntities = likeRepository.findByStoreNumber(storeNumber);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetLikedStoreResponseDto.success(likeEntities);

  }

  @Override
  public ResponseEntity<? super GetLikedStoreReviewRatingResponseDto> getUserLikedStoreInfo(String userId) {
    
    List<LikeStoreReviewNRating> list = new ArrayList<>();

    try {
      List<LikeEntity> likeEntities = likeRepository.findByUserId(userId);
      for(LikeEntity likeEntity: likeEntities) {
        Integer storeNumber = likeEntity.getStoreNumber();
        StoreEntity storeEntity = storeRepository.findByStoreNumber(storeNumber);
        
        String storeName = storeEntity.getStoreName();
        List<ReviewEntity> reviewEntities = reviewRepository.findByStoreName(storeName);
        Integer reviewCount = reviewEntities.size();

        Integer sum = 0;
        Double reviewRating = 0.0;
        if(reviewEntities.size() != 0) {
          for(ReviewEntity reviewEntity: reviewEntities) sum = sum + reviewEntity.getReviewRating();
          reviewRating =  (sum / (double)reviewEntities.size());
        }

        LikeStoreReviewNRating one = new LikeStoreReviewNRating(storeNumber, reviewRating, reviewCount);
        list.add(one);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.databaseError();
    }
    return GetLikedStoreReviewRatingResponseDto.success(list);
  }

}
