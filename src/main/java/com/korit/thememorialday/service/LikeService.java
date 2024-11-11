package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.like.PostLikeStoreRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.like.GetLikeStoreListResponseDto;
import com.korit.thememorialday.dto.response.like.GetLikedStoreResponseDto;
import com.korit.thememorialday.dto.response.like.GetLikedStoreReviewRatingResponseDto;
import com.korit.thememorialday.dto.response.like.GetUserLikeListResponseDto;

public interface LikeService {

  ResponseEntity<ResponseDto> postLike(PostLikeStoreRequestDto dto);

  ResponseEntity<ResponseDto> deleteLike(String userId, Integer storeNumber);

  ResponseEntity<? super GetLikeStoreListResponseDto> getStoreNumberList(String userId);

  ResponseEntity<? super GetUserLikeListResponseDto> getUserLikeList(String userId);

  public ResponseEntity<? super GetLikedStoreResponseDto> getStoreLike(Integer storeNumber);

  ResponseEntity<? super GetLikedStoreReviewRatingResponseDto> getUserLikedStoreInfo(String userId);

}
