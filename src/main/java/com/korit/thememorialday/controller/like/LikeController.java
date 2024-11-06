package com.korit.thememorialday.controller.like;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.like.PostLikeStoreRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.service.LikeService;
import com.korit.thememorialday.dto.response.like.GetLikeStoreListResponseDto;
import com.korit.thememorialday.dto.response.like.GetLikedStoreResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class LikeController {

  private final LikeService likeService;

  @PostMapping(value = { "", "/" })
  public ResponseEntity<ResponseDto> postLike(
      @RequestBody @Valid PostLikeStoreRequestDto requestBody) {
    ResponseEntity<ResponseDto> response = likeService.postLike(requestBody);
    return response;
  }

  @GetMapping(value = { "/like/{storeNumber}" })
  public ResponseEntity<? super GetLikedStoreResponseDto> getStoreLike(
      @PathVariable("storeNumber") Integer storeNumber) {
    ResponseEntity<? super GetLikedStoreResponseDto> response = likeService.getStoreLike(storeNumber);
    return response;
  }

  @GetMapping(value = { "/like" })
  public ResponseEntity<? super GetLikeStoreListResponseDto> getStoreNumberList(
      @RequestParam String userId) {
    ResponseEntity<? super GetLikeStoreListResponseDto> response = likeService.getStoreNumberList(userId);
    return response;
  }

  @DeleteMapping(value = { "", "/" })
  public ResponseEntity<ResponseDto> deleteLike(
      @RequestParam String userId,
      @RequestParam Integer storeNumber) {
    ResponseEntity<ResponseDto> response = likeService.deleteLike(userId, storeNumber);
    return response;
  }

}
