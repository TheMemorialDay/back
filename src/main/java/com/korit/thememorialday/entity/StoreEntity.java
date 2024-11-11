package com.korit.thememorialday.entity;

import com.korit.thememorialday.dto.request.store.PatchStoreRegisterRequestDto;
import com.korit.thememorialday.dto.request.store.PostStoreRegisterRequestDto;

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
@Entity(name = "store")
@Table(name = "store")
public class StoreEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
  private Integer storeNumber;
  private String userId;
  private String storeName;
  private String storeIntroduce;
  private String storeParticular;
  private String storeContact;
  private String storeCaution;
  private String storeAddress;
  private String storeDetailAddress;
  private String storeGugun;
  private String storeDong;
  private String storeLatitude;
  private String storeLongtitude;
  private String storeTel;
  private Float storeRating;
  private Integer reviewCount;
  private Integer likeCount;
  private String storeImageUrl;
  //
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

  //* FOR 픽업가능요일 필터
  public boolean isMondayOpen() {
    // 해당 요일 오픈이 null이 아니면서 비어있지 않은 것
    return mondayOpen != null && !mondayOpen.isEmpty();
  }

  public boolean isTuesDayOpen() {
    return tuesdayOpen != null && !tuesdayOpen.isEmpty();
  }

  public boolean isWednsDayOpen() {
    return wednesdayOpen != null && !wednesdayOpen.isEmpty();
  }

  public boolean isThursDayOpen() {
    return thursdayOpen != null && !thursdayOpen.isEmpty();
  }

  public boolean isFriDayOpen() {
    return fridayOpen != null && !fridayOpen.isEmpty();
  }

  public boolean isSaturDayOpen() {
    return saturdayOpen != null && !saturdayOpen.isEmpty();
  }

  public boolean isSunDayOpen() {
    return sundayOpen != null && !sundayOpen.isEmpty();
  }

  public StoreEntity(PostStoreRegisterRequestDto dto) { // 생성용도
    this.userId = dto.getUserId();
    this.storeName = dto.getStoreName();
    this.storeIntroduce = dto.getStoreIntroduce();
    this.storeParticular = dto.getStoreParticular();
    this.storeContact = dto.getStoreContact();
    this.storeCaution = dto.getStoreCaution();
    this.storeAddress = dto.getStoreAddress();
    this.storeDetailAddress = dto.getStoreDetailAddress();
    this.storeGugun = dto.getStoreGugun();
    this.storeDong = dto.getStoreDong();
    this.storeLatitude = dto.getStoreLatitude();
    this.storeLongtitude = dto.getStoreLongtitude();
    this.storeTel = dto.getStoreTel();
    this.storeRating = dto.getStoreRating();
    this.reviewCount = dto.getReviewCount();
    this.likeCount = dto.getLikeCount();
    this.storeImageUrl = dto.getStoreImageUrl();
    this.mondayOpen = dto.getMondayOpen();
    this.mondayLast = dto.getMondayLast();
    this.tuesdayOpen = dto.getTuesdayOpen();
    this.tuesdayLast = dto.getThursdayLast();
    this.wednesdayOpen = dto.getWednesdayOpen();
    this.wednesdayLast = dto.getWednesdayLast();
    this.thursdayOpen = dto.getThursdayOpen();
    this.thursdayLast = dto.getThursdayLast();
    this.fridayOpen = dto.getFridayOpen();
    this.fridayLast = dto.getFridayLast();
    this.saturdayOpen = dto.getSaturdayOpen();
    this.saturdayLast = dto.getSaturdayLast();
    this.sundayOpen = dto.getSundayOpen();
    this.sundayLast = dto.getSundayLast();
  }

  public void patch(PatchStoreRegisterRequestDto dto) { // 수정용도
    this.userId = dto.getUserId();
    this.storeName = dto.getStoreName();
    this.storeIntroduce = dto.getStoreIntroduce();
    this.storeParticular = dto.getStoreParticular();
    this.storeContact = dto.getStoreContact();
    this.storeCaution = dto.getStoreCaution();
    this.storeAddress = dto.getStoreAddress();
    this.storeDetailAddress = dto.getStoreDetailAddress();
    this.storeGugun = dto.getStoreGugun();
    this.storeDong = dto.getStoreDong();
    this.storeLatitude = dto.getStoreLatitude();
    this.storeLongtitude = dto.getStoreLongtitude();
    this.storeTel = dto.getStoreTel();
    this.storeRating = dto.getStoreRating();
    this.reviewCount = dto.getReviewCount();
    this.likeCount = dto.getLikeCount();
    this.storeImageUrl = dto.getStoreImageUrl();
    this.mondayOpen = dto.getMondayOpen();
    this.mondayLast = dto.getMondayLast();
    this.tuesdayOpen = dto.getTuesdayOpen();
    this.tuesdayLast = dto.getThursdayLast();
    this.wednesdayOpen = dto.getWednesdayOpen();
    this.wednesdayLast = dto.getWednesdayLast();
    this.thursdayOpen = dto.getThursdayOpen();
    this.thursdayLast = dto.getThursdayLast();
    this.fridayOpen = dto.getFridayOpen();
    this.fridayLast = dto.getFridayLast();
    this.saturdayOpen = dto.getSaturdayOpen();
    this.saturdayLast = dto.getSaturdayLast();
    this.sundayOpen = dto.getSundayOpen();
    this.sundayLast = dto.getSundayLast();
  }
}
