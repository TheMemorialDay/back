package com.korit.thememorialday.dto.response.like;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.Like;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.repository.resultSet.GetLikeUserResultSet;

import lombok.Getter;

@Getter
public class GetUserLikeListResponseDto extends ResponseDto {

  private List<Like> likes;

  private GetUserLikeListResponseDto(List<GetLikeUserResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.likes = Like.getList(resultSets);
  }

  public static ResponseEntity<GetUserLikeListResponseDto> success(List<GetLikeUserResultSet> resultSets) {
    GetUserLikeListResponseDto responseBody = new GetUserLikeListResponseDto(resultSets);
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }
}
