package com.korit.thememorialday.dto.response.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.Review;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class GetReviewListResponseDto extends ResponseDto {

    private List<Review> reviews;

    private GetReviewListResponseDto(List<Review> reviews) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviews = reviews;
    }

    public static ResponseEntity<GetReviewListResponseDto> success(List<Review> reviews) {
        GetReviewListResponseDto responseBody = new GetReviewListResponseDto(reviews);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
