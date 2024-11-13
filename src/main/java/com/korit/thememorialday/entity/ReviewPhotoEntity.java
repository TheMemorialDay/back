package com.korit.thememorialday.entity;

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
@Entity(name="review_photo")
@Table(name="review_photo")
public class ReviewPhotoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewPhotoNumber;
    private Integer reviewNumber;
    private String reviewPhotoUrl;

    public ReviewPhotoEntity(Integer reviewNumber, String imageUrl) {
        this.reviewNumber = reviewNumber;
        this.reviewPhotoUrl = imageUrl;
    }
}
