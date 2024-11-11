package com.korit.thememorialday.common.object;

import lombok.Getter;

@Getter
public class LikeStoreReviewNRating {
    private Integer storeNumber;
    private Double reviewRating;
    private Integer reviewCount;

    public LikeStoreReviewNRating(Integer storeNumber, Double reviewRating, Integer reviewCount) {
        this.reviewRating = reviewRating;
        this.reviewCount = reviewCount;
        this.storeNumber = storeNumber;
    }
}
