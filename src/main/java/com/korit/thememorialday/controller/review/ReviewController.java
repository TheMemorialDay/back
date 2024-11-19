package com.korit.thememorialday.controller.review;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.review.PostReviewRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.review.GetMyReviewListResponseDto;
import com.korit.thememorialday.dto.response.review.GetReviewListResponseDto;
import com.korit.thememorialday.service.review.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/mypage/order-detail/write-review")
    public ResponseEntity<ResponseDto> postReview(
        @RequestBody PostReviewRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = reviewService.postReview(requestBody);
        return response;
    }

    @GetMapping("/stores/{storeNumber}/review/list")
    public ResponseEntity<? super GetReviewListResponseDto> getReviewList(
        @PathVariable("storeNumber") Integer storeNumber
    ) {
        ResponseEntity<? super GetReviewListResponseDto> response = reviewService.getReviewList(storeNumber);
        return response;
    }

    @GetMapping("/mypage/review")
    public ResponseEntity<? super GetMyReviewListResponseDto> getMyReviewList(
        @RequestParam("userId") String userId
    ) {
        ResponseEntity<? super GetMyReviewListResponseDto> response = reviewService.getMyReviewList(userId);
        return response;
    }
}
