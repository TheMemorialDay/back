package com.korit.thememorialday.service.implement.review;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.common.object.MyReview;
import com.korit.thememorialday.common.object.Review;
import com.korit.thememorialday.dto.request.review.PostReviewRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.review.GetMyReviewListResponseDto;
import com.korit.thememorialday.dto.response.review.GetReviewListResponseDto;
import com.korit.thememorialday.entity.ReviewEntity;
import com.korit.thememorialday.entity.ReviewPhotoEntity;
import com.korit.thememorialday.entity.StoreEntity;
import com.korit.thememorialday.repository.ReviewPhotoRepository;
import com.korit.thememorialday.repository.ReviewRepository;
import com.korit.thememorialday.repository.StoreRepository;
import com.korit.thememorialday.service.review.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplement implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    @Override
    public ResponseEntity<ResponseDto> postReview(PostReviewRequestDto dto) {
        try {
            ReviewEntity reviewEntity = new ReviewEntity(dto);
            reviewRepository.save(reviewEntity);
            Integer reviewNumber = reviewEntity.getReviewNumber();

            for (String imageUrl : dto.getImageUrls()) {
                ReviewPhotoEntity reviewPhotoEntity = new ReviewPhotoEntity(reviewNumber, imageUrl);
                reviewPhotoRepository.save(reviewPhotoEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetReviewListResponseDto> getReviewList(Integer storeNumber) {
        List<Review> reviews = new ArrayList<>();

        try {
            StoreEntity storeEntity = storeRepository.findByStoreNumber(storeNumber);
            if(storeEntity == null) return ResponseDto.noExistStore();

            List<ReviewEntity> reviewEntities = reviewRepository.findByStoreNameOrderByReviewDayDesc(storeEntity.getStoreName());
            for(ReviewEntity reviewEntity: reviewEntities) {
                Integer reviewNumber = reviewEntity.getReviewNumber();
                Integer reviewRating = reviewEntity.getReviewRating();
                Date reviewDate = reviewEntity.getReviewDay();
                String reviewContents = reviewEntity.getReviewContents();
                String productName = reviewEntity.getProductName();
    
                List<String> reviewPhotos = new ArrayList<>();
                List<ReviewPhotoEntity> reviewPhotoEntities = reviewPhotoRepository.findByReviewNumber(reviewNumber);
                for(ReviewPhotoEntity reviewPhotoEntity: reviewPhotoEntities) {
                    reviewPhotos.add(reviewPhotoEntity.getReviewPhotoUrl());
                }
    
                Review review = new Review(reviewRating, reviewDate, reviewContents, 
                reviewPhotos, productName);
                reviews.add(review);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetReviewListResponseDto.success(reviews);
    }

    @Override
    public ResponseEntity<? super GetMyReviewListResponseDto> getMyReviewList(String userId) {
        List<MyReview> myReviews = new ArrayList<>();
        try {
            List<ReviewEntity> reviewEntities = reviewRepository.findByUserIdOrderByReviewDayDesc(userId);
            if(reviewEntities == null) return ResponseDto.noExistUserId();

            for(ReviewEntity reviewEntity: reviewEntities) {
                Integer reviewNumber = reviewEntity.getReviewNumber();
                String storeName = reviewEntity.getStoreName();
                StoreEntity storeEntity = storeRepository.findByStoreName(storeName);
                Integer storeNumber = storeEntity.getStoreNumber();

                List<String> reviewPhotos = new ArrayList<>();
                List<ReviewPhotoEntity> reviewPhotoEntities = reviewPhotoRepository.findByReviewNumber(reviewNumber);
                for(ReviewPhotoEntity reviewPhotoEntity: reviewPhotoEntities) {
                    reviewPhotos.add(reviewPhotoEntity.getReviewPhotoUrl());
                }

                MyReview myReview = new MyReview(storeNumber, reviewEntity, reviewPhotos);
                myReviews.add(myReview);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetMyReviewListResponseDto.success(myReviews);
    }   
}
