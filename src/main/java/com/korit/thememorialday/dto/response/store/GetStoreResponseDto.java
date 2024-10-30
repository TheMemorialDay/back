package com.korit.thememorialday.dto.response.store;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.StoreEntity;

import lombok.Getter;

@Getter
public class GetStoreResponseDto extends ResponseDto {

  private Integer storeNumber;
  private String userId;
  private String storeName;
  private String storeIntroduce;
  private String storeParticular;
  private String storeContact;
  private String storeCaution;
  private String storeAddress;
  private String storeGugun;
  private String storeDong;
  private String storeTel;
  private String storeLatitude;
  private String storeLongtitude;
  private Float storeRating;
  private Integer reviewCount;
  private Integer likeCount;
  private String storeImageUrl;
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
  private String sundayOpen;
  private String sundayLast;

  private GetStoreResponseDto(StoreEntity storeEntity) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.storeNumber = storeEntity.getStoreNumber();
    this.userId = storeEntity.getUserId();
    this.storeName = storeEntity.getStoreName();
    this.storeIntroduce = storeEntity.getStoreIntroduce();
    this.storeParticular = storeEntity.getStoreParticular();
    this.storeContact = storeEntity.getStoreContact();
    this.storeCaution = storeEntity.getStoreCaution();
    this.storeAddress = storeEntity.getStoreAddress();
    this.storeGugun = storeEntity.getStoreGugun();
    this.storeDong = storeEntity.getStoreDong();
    this.storeLatitude = storeEntity.getStoreLatitude();
    this.storeLongtitude = storeEntity.getStoreLongtitude();
    this.storeTel = storeEntity.getStoreTel();
    this.storeRating = storeEntity.getStoreRating();
    this.reviewCount = storeEntity.getReviewCount();
    this.likeCount = storeEntity.getLikeCount();
    this.storeImageUrl = storeEntity.getStoreImageUrl();
    this.mondayOpen = storeEntity.getMondayOpen();
    this.mondayLast = storeEntity.getMondayLast();
    this.tuesdayOpen = storeEntity.getTuesdayOpen();
    this.tuesdayLast = storeEntity.getThursdayLast();
    this.wednesdayOpen = storeEntity.getWednesdayOpen();
    this.wednesdayLast = storeEntity.getWednesdayLast();
    this.thursdayOpen = storeEntity.getThursdayOpen();
    this.thursdayLast = storeEntity.getThursdayLast();
    this.fridayOpen = storeEntity.getFridayOpen();
    this.fridayLast = storeEntity.getFridayLast();
    this.saturdayOpen = storeEntity.getSaturdayOpen();
    this.saturdayLast = storeEntity.getSaturdayLast();
    this.sundayOpen = storeEntity.getSundayOpen();
    this.sundayLast = storeEntity.getSundayLast();
  }

  public static ResponseEntity<GetStoreResponseDto> success(StoreEntity storeEntity) {
    GetStoreResponseDto responseBody = new GetStoreResponseDto(storeEntity);
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }

}