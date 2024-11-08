package com.korit.thememorialday.dto.response.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.MyReview;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetMyReviewListResponseDto extends ResponseDto{
    private List<MyReview> myReviews;

    private GetMyReviewListResponseDto(List<MyReview> myReviews) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.myReviews = myReviews;
    }

    public static ResponseEntity<GetMyReviewListResponseDto> success(List<MyReview> myReviews) {
        GetMyReviewListResponseDto responseBody = new GetMyReviewListResponseDto(myReviews);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
