package com.korit.thememorialday.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.dto.request.like.PostLikeStoreRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.entity.LikeEntity;
import com.korit.thememorialday.repository.LikeRepository;
import com.korit.thememorialday.service.LikeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImplement implements LikeService {

  private final LikeRepository likeRepository;

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

}
