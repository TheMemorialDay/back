package com.korit.thememorialday.service.review;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.review.PostReviewRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.review.GetMyReviewListResponseDto;
import com.korit.thememorialday.dto.response.review.GetReviewListResponseDto;

public interface ReviewService {
    ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto);
    ResponseEntity<? super GetReviewListResponseDto> getReviewList(Integer storeNumber);
    ResponseEntity<? super GetMyReviewListResponseDto> getMyReviewList(String userId);
}
