package com.korit.thememorialday.entity;

import java.util.Date;

import com.korit.thememorialday.dto.request.review.PostReviewRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="review")
@Table(name="review")
public class ReviewEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewNumber;
    private String orderCode;
    private Integer reviewRating;
    private Date reviewDay;
    private String reviewContents;
    private String storeName;
    private String productName;
    private String userId;

    public ReviewEntity(ReviewEntity reviewEntity) {
        this.reviewNumber = reviewEntity.getReviewNumber();
        this.orderCode = reviewEntity.getOrderCode();
        this.reviewRating = reviewEntity.getReviewRating();
        this.reviewDay = reviewEntity.getReviewDay();
        this.reviewContents = reviewEntity.getReviewContents();
        this.storeName = reviewEntity.getStoreName();
        this.productName = reviewEntity.getProductName();
        this.userId = reviewEntity.getUserId();
    }

    public ReviewEntity(PostReviewRequestDto dto) {
        this.orderCode = dto.getOrderCode();
        this.reviewRating = dto.getReviewRating();
        this.reviewDay = dto.getReviewDay();
        this.reviewContents = dto.getReviewContents();
        this.storeName = dto.getStoreName();
        this.productName = dto.getProductName();
        this.userId = dto.getUserId();
    }
}
