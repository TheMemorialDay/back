package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.like.PostLikeStoreRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;

public interface LikeService {

  ResponseEntity<ResponseDto> postLike(PostLikeStoreRequestDto dto);

  ResponseEntity<ResponseDto> deleteLike(String userId, Integer storeNumber);
}
