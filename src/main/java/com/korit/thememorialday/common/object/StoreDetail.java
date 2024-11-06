package com.korit.thememorialday.common.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.korit.thememorialday.entity.LikeEntity;
import com.korit.thememorialday.entity.ProductEntity;
import com.korit.thememorialday.entity.StoreEntity;
import com.korit.thememorialday.entity.ThemaEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDetail {
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
  private List<String> likeList;
  private List<Boolean> productToday;
  private List<String> productTag;
  private List<List<String>> themes;

  public StoreDetail(StoreEntity storeEntity, List<LikeEntity> likeEntities, List<ProductEntity> productEntities,
      List<ThemaEntity> themaEntities) {
    List<String> likeList = new ArrayList<>();
    for (LikeEntity likeEntity : likeEntities)
      likeList.add(likeEntity.getUserId());

    List<Boolean> productToday = new ArrayList<>();
    for (ProductEntity productEntity : productEntities)
      productToday.add(productEntity.isProductToday());

    List<String> productTag = new ArrayList<>();
    for (ProductEntity productEntity : productEntities)
      productTag.add(productEntity.getProductTag());

    // 테마들을 제품별로 그룹화
    Map<Integer, List<String>> themeMap = new HashMap<>();
    for (ThemaEntity themaEntity : themaEntities) {
      themeMap
          .computeIfAbsent(themaEntity.getProductNumber(), k -> new ArrayList<>())
          .add(themaEntity.getThema());
    }

    List<List<String>> groupedThemes = new ArrayList<>();
    for (Integer productNumber : themeMap.keySet()) {
      groupedThemes.add(themeMap.get(productNumber));
    }

    this.storeNumber = storeEntity.getStoreNumber();
    this.userId = storeEntity.getUserId();
    this.storeName = storeEntity.getStoreName();
    this.storeIntroduce = storeEntity.getStoreIntroduce();
    this.storeParticular = storeEntity.getStoreParticular();
    this.storeContact = storeEntity.getStoreContact();
    this.storeCaution = storeEntity.getStoreCaution();
    this.storeAddress = storeEntity.getStoreAddress();
    this.storeDetailAddress = storeEntity.getStoreDetailAddress();
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
    this.likeList = likeList;
    this.productTag = productTag;
    this.productToday = productToday;
    this.themes = groupedThemes;
  }
}
