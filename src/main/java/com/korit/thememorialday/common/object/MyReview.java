package com.korit.thememorialday.common.object;

import java.util.Date;
import java.util.List;

import com.korit.thememorialday.entity.ReviewEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyReview {
    private Integer storeNumber;
    private String storeName;
    private Integer reviewRating;
    private Date reviewDay;
    private String reviewContents;
    private String productName;
    private List<String> imageUrls;

    public MyReview(Integer storeNumber, ReviewEntity reviewEntity, List<String> reviewPhotoUrls) {
        this.storeNumber = storeNumber;
        this.reviewRating = reviewEntity.getReviewRating();
        this.reviewDay = reviewEntity.getReviewDay();
        this.reviewContents = reviewEntity.getReviewContents();
        this.productName = reviewEntity.getProductName();
        this.imageUrls = reviewPhotoUrls;
        this.storeName = reviewEntity.getStoreName();
    }
}
