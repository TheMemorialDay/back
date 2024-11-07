package com.korit.thememorialday.common.object;

import java.util.Date;
import java.util.List;

import com.korit.thememorialday.entity.ReviewEntity;

import lombok.Getter;

@Getter
public class Review {
    private Integer reviewRating;
    private Date reviewDay;
    private String reviewContents;
    private String productName;
    private List<String> imageUrls;

    public Review(ReviewEntity reviewEntity, List<String> reviewPhotoUrls) {
        this.reviewRating = reviewEntity.getReivewNumber();
        this.reviewDay = reviewEntity.getReviewDay();
        this.reviewContents = reviewEntity.getReviewContents();
        this.productName = reviewEntity.getProductName();
        this.imageUrls = reviewPhotoUrls;
    }
}
