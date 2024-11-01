package com.korit.thememorialday.controller.like;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.like.PostLikeStoreRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.service.LikeService;

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

  @DeleteMapping(value = { "", "/" })
  public ResponseEntity<ResponseDto> deleteLike(
      @RequestParam String userId,
      @RequestParam Integer storeNumber) {
    ResponseEntity<ResponseDto> response = likeService.deleteLike(userId, storeNumber);
    return response;
  }
}
