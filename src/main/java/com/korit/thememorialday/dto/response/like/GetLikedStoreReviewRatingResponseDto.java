package com.korit.thememorialday.dto.response.like;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.LikeStoreReviewNRating;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetLikedStoreReviewRatingResponseDto extends ResponseDto{
    private List<LikeStoreReviewNRating> reviewNRatings;

    private GetLikedStoreReviewRatingResponseDto(List<LikeStoreReviewNRating> reviewNRatings) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewNRatings = reviewNRatings;
    }

    public static ResponseEntity<GetLikedStoreReviewRatingResponseDto> success(List<LikeStoreReviewNRating> reviewNRatings) {
        GetLikedStoreReviewRatingResponseDto responseBody = new GetLikedStoreReviewRatingResponseDto(reviewNRatings);
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }
}
