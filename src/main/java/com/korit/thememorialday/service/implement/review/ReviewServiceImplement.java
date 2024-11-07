package com.korit.thememorialday.service.implement.review;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.dto.request.review.PostReviewRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.entity.ReviewEntity;
import com.korit.thememorialday.entity.order.OrderEntity;
import com.korit.thememorialday.repository.ReviewRepository;
import com.korit.thememorialday.repository.order.OrderRepository;
import com.korit.thememorialday.service.review.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplement implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;

    @Override
    public ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto) {
        try {
            //OrderEntity orderEntity = orderRepository.findByOrderCode(orderCode);
            //if(orderEntity == null) return ResponseDto.noExistOrder();

            ReviewEntity reviewEntity = new ReviewEntity(dto);
            reviewRepository.save(reviewEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
    
}
