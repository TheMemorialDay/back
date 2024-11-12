package com.korit.thememorialday.controller.like;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.response.like.GetLikedStoreResponseDto;
import com.korit.thememorialday.dto.response.like.GetLikedStoreReviewRatingResponseDto;
import com.korit.thememorialday.dto.response.like.GetUserLikeListResponseDto;
import com.korit.thememorialday.service.LikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage/like")
@RequiredArgsConstructor
public class MyPageLikeController {

  private final LikeService likeService;

  @GetMapping(value = { "/", "" })
  public ResponseEntity<? super GetUserLikeListResponseDto> getUserLikeList(
      @RequestParam("userId") String userId) {
    ResponseEntity<? super GetUserLikeListResponseDto> response = likeService.getUserLikeList(userId);
    return response;
  }

  @GetMapping(value = "/{userId}")
  public ResponseEntity<? super GetUserLikeListResponseDto> getLikeList(
      @PathVariable("userId") String userId) {
    ResponseEntity<? super GetUserLikeListResponseDto> response = likeService.getUserLikeList(userId);
    return response;
  }

  @GetMapping(value = { "/{userId}/{storeNumber}" })
  public ResponseEntity<? super GetLikedStoreResponseDto> getStoreLike(
      @PathVariable("userId") String userId,
      @PathVariable("storeNumber") Integer storeNumber) {
    ResponseEntity<? super GetLikedStoreResponseDto> response = likeService.getStoreLike(storeNumber);
    return response;
  }

  @GetMapping(value = "/{userId}/info")
  public ResponseEntity<? super GetLikedStoreReviewRatingResponseDto> getUserLikedStoreInfo(
    @PathVariable("userId") String userId  
  ) {
    ResponseEntity<? super GetLikedStoreReviewRatingResponseDto> response = likeService.getUserLikedStoreInfo(userId);
    return response;
  }
}
