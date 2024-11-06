package com.korit.thememorialday.dto.response.like;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.Like;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.LikeEntity;

import lombok.Getter;

@Getter
public class GetLikeStoreListResponseDto extends ResponseDto {

  private List<Like> likes;

  private GetLikeStoreListResponseDto(List<LikeEntity> likeEntities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.likes = Like.getStoreNumberList(likeEntities);
  }

  public static ResponseEntity<GetLikeStoreListResponseDto> success(List<LikeEntity> likeEntities) {
    GetLikeStoreListResponseDto responseBody = new GetLikeStoreListResponseDto(likeEntities);
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }
}
