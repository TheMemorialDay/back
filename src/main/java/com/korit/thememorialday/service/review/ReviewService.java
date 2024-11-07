package com.korit.thememorialday.service.review;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.review.PostReviewRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;

public interface ReviewService {
    ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto);
}
