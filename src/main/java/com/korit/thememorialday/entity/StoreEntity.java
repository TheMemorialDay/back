package com.korit.thememorialday.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "store")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_number")
    private Integer storeNumber; // 가게번호

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; // 유저 아이디

    @Column(name = "store_name", nullable = false, length = 45)
    private String storeName; // 가게명

    @Column(name = "store_introduce", columnDefinition = "TEXT")
    private String storeIntroduce; // 가게소개

    @Column(name = "store_particular", columnDefinition = "TEXT")
    private String storeParticular; // 가게 상세소개글

    @Column(name = "store_contact", columnDefinition = "TEXT")
    private String storeContact; // 문의하기

    @Column(name = "store_caution", nullable = false, columnDefinition = "TEXT")
    private String storeCaution; // 가게 유의사항

    @Column(name = "store_address", nullable = false, length = 255)
    private String storeAddress; // 가게 주소

    @Column(name = "store_gugun", nullable = false, length = 10)
    private String storeGugun; // 가게 구/군

    @Column(name = "store_dong", nullable = false, length = 10)
    private String storeDong; // 가게 읍/동

    @Column(name = "store_tel", length = 20)
    private String storeTel; // 가게 연락처

    @Column(name = "store_rating", nullable = false)
    private Float storeRating = 0f; // 가게 평점

    @Column(name = "review_count", nullable = false)
    private Integer reviewCount = 0; // 리뷰 개수

    @Column(name = "like_count", nullable = false)
    private Integer likeCount = 0; // 찜한 횟수

    @Column(name = "store_image_url", nullable = false, columnDefinition = "TEXT")
    private String storeImageUrl; // 가게 썸네일 이미지 URL

    @Column(name = "sunday_open", length = 5)
    private String sundayOpen;

    @Column(name = "sunday_last", length = 5)
    private String sundayLast;

    @Column(name = "monday_open", length = 5)
    private String mondayOpen;

    @Column(name = "monday_last", length = 5)
    private String mondayLast;

    @Column(name = "tuesday_open", length = 5)
    private String tuesdayOpen;

    @Column(name = "tuesday_last", length = 5)
    private String tuesdayLast;

    @Column(name = "wednesday_open", length = 5)
    private String wednesdayOpen;

    @Column(name = "wednesday_last", length = 5)
    private String wednesdayLast;

    @Column(name = "thursday_open", length = 5)
    private String thursdayOpen;

    @Column(name = "thursday_last", length = 5)
    private String thursdayLast;

    @Column(name = "friday_open", length = 5)
    private String fridayOpen;

    @Column(name = "friday_last", length = 5)
    private String fridayLast;

    @Column(name = "saturday_open", length = 5)
    private String saturdayOpen;

    @Column(name = "saturday_last", length = 5)
    private String saturdayLast;
}
