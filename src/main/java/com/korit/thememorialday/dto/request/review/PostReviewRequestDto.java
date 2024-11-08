package com.korit.thememorialday.dto.request.review;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostReviewRequestDto {
    
    @NotBlank
    private String orderCode;

    @NotNull
    private Integer reviewRating;

    private Date reviewDay;

    private String reviewContents;

    @NotNull
    private String storeName;

    @NotNull
    private String productName;

    @NotNull
    private String userId;

    private List<String> imageUrls;
}
