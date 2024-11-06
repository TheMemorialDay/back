package com.korit.thememorialday.dto.response.like;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.LikeEntity;

import lombok.Getter;

@Getter
public class GetLikedStoreResponseDto extends ResponseDto {
  private List<String> likeList;

  private GetLikedStoreResponseDto(List<LikeEntity> likeEntities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    List<String> likeList = new ArrayList<>();
    for (LikeEntity likeEntity : likeEntities)
      likeList.add(likeEntity.getUserId());
    this.likeList = likeList;
  }

  public static ResponseEntity<GetLikedStoreResponseDto> success(List<LikeEntity> likeEntities) {
    GetLikedStoreResponseDto responseBody = new GetLikedStoreResponseDto(likeEntities);
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }
}
