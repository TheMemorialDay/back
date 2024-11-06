package com.korit.thememorialday.common.object;

import java.util.ArrayList;
import java.util.List;

import com.korit.thememorialday.entity.LikeEntity;
import com.korit.thememorialday.repository.resultSet.GetLikeUserResultSet;
import lombok.Getter;

@Getter
public class Like {

  private String userId;
  private Integer storeNumber;
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
  private String storeImageUrl;
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

  // GetLikeUserResultSet에서 필드들을 초기화하는 생성자
  private Like(GetLikeUserResultSet resultSet) {
    this.userId = resultSet.getUserId();
    this.storeNumber = resultSet.getStoreNumber();
    this.storeName = resultSet.getStoreName();
    this.storeIntroduce = resultSet.getStoreIntroduce();
    this.storeParticular = resultSet.getStoreParticular();
    this.storeContact = resultSet.getStoreContact();
    this.storeCaution = resultSet.getStoreCaution();
    this.storeAddress = resultSet.getStoreAddress();
    this.storeDetailAddress = resultSet.getStoreDetailAddress();
    this.storeGugun = resultSet.getStoreGugun();
    this.storeDong = resultSet.getStoreDong();
    this.storeLatitude = resultSet.getStoreLatitude();
    this.storeLongtitude = resultSet.getStoreLongtitude();
    this.storeTel = resultSet.getStoreTel();
    this.storeImageUrl = resultSet.getStoreImageUrl();
    this.sundayOpen = resultSet.getSundayOpen();
    this.sundayLast = resultSet.getSundayLast();
    this.mondayOpen = resultSet.getMondayOpen();
    this.mondayLast = resultSet.getMondayLast();
    this.tuesdayOpen = resultSet.getTuesdayOpen();
    this.tuesdayLast = resultSet.getTuesdayLast();
    this.wednesdayOpen = resultSet.getWednesdayOpen();
    this.wednesdayLast = resultSet.getWednesdayLast();
    this.thursdayOpen = resultSet.getThursdayOpen();
    this.thursdayLast = resultSet.getThursdayLast();
    this.fridayOpen = resultSet.getFridayOpen();
    this.fridayLast = resultSet.getFridayLast();
    this.saturdayOpen = resultSet.getSaturdayOpen();
    this.saturdayLast = resultSet.getSaturdayLast();
  }

  public Like(LikeEntity likeEntity) {
    this.userId = likeEntity.getUserId();
    this.storeNumber = likeEntity.getStoreNumber();
  }

  public static List<Like> getList(List<GetLikeUserResultSet> resultSets) {
    List<Like> likes = new ArrayList<>();
    for (GetLikeUserResultSet resultSet : resultSets) {
      likes.add(new Like(resultSet));
    }
    return likes;
  }

  public static List<Like> getStoreNumberList(List<LikeEntity> likeEntites) {
    List<Like> likes = new ArrayList<>();
    for (LikeEntity likeEntity : likeEntites) {
      Like like = new Like(likeEntity);
      likes.add(like);
    }
    return likes;
  }
}
