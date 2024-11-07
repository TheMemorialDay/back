package com.korit.thememorialday.common.object;


import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Integer reviewRating;
    private Date reviewDay;
    private String reviewContents;
    private String productName;
    private List<String> imageUrls;

    public Review(Integer reviewRating, Date reviewDay, String reviewContents, 
        List<String> reviewPhotoUrls, String productName) {
        this.reviewRating = reviewRating;
        this.reviewDay = reviewDay;
        this.reviewContents = reviewContents;
        this.productName = productName;
        this.imageUrls = reviewPhotoUrls;
    }
}
