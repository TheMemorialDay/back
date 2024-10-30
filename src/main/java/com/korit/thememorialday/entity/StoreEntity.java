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
    private Integer storeNumber; // 가게번호
    private UserEntity user; // 유저 아이디
    private String storeName; // 가게명
    private String storeIntroduce; // 가게소개
    private String storeParticular; // 가게 상세소개글
    private String storeContact; // 문의하기
    private String storeCaution; // 가게 유의사항
    private String storeAddress; // 가게 주소
    private String storeGugun; // 가게 구/군
    private String storeDong; // 가게 읍/동
    private String storeTel; // 가게 연락처
    private Float storeRating = 0f; // 가게 평점
    private Integer reviewCount = 0; // 리뷰 개수
    private Integer likeCount = 0; // 찜한 횟수
    private String storeImageUrl; // 가게 썸네일 이미지 URL
    private String sundayOpen;
    private String sundayLast;
    private String mondayOpen;
    private String mondayLast;
    private String tuesdayOpen;
    private String tuesdayLast;
    private String wednesdayOpen;
    private String wednesdayLast;
    private String thursdayOpen;
    private String thursdayLast;
    private String fridayOpen;
    private String fridayLast;
    private String saturdayOpen;
    private String saturdayLast;
}
