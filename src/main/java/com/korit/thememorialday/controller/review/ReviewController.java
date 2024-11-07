package com.korit.thememorialday.controller.review;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.review.PostReviewRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.service.review.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage/order-detail")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    /*
     * @PostMapping("/write")
    public ResponseEntity<ResponseDto> postQnA(
        @RequestBody @Valid PostQnARequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = qnaService.postQnA(requestBody);
        return response;
    }
     */

    @PostMapping("/write-review")
    public ResponseEntity<ResponseDto> postReview(
        @RequestBody PostReviewRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = reviewService.postReview(requestBody);
        return response;
    }
}
