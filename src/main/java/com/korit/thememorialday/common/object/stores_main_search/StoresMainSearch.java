package com.korit.thememorialday.common.object.stores_main_search;

import java.util.ArrayList;
import java.util.List;

import com.korit.thememorialday.entity.StoreEntity;

import lombok.Getter;

//# store main page에서 가게명 & 상품명 검색 - 가게 리스트 객체

@Getter
public class StoresMainSearch {
	private Integer storeNumber;
	private String storeImageUrl;
	private String storeName;
	private String storeGugun;
	private String storeDong;
	private Float storeRating;
    private Integer reviewCount;
    private Integer likeCount; 

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

	private StoresMainSearch(StoreEntity storeEntity) {
		this.storeNumber = storeEntity.getStoreNumber();
		this.storeImageUrl = storeEntity.getStoreImageUrl();
		this.storeName = storeEntity.getStoreName();
		this.storeGugun = storeEntity.getStoreGugun();
		this.storeDong = storeEntity.getStoreDong();
		this.storeRating = storeEntity.getStoreRating();
		this.reviewCount = storeEntity.getReviewCount();
		this.likeCount = storeEntity.getLikeCount();

		this.mondayOpen = storeEntity.getMondayOpen();
		this.mondayLast = storeEntity.getMondayLast();
		this.tuesdayOpen = storeEntity.getTuesdayOpen();
		this.tuesdayLast = storeEntity.getTuesdayLast();
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

	// List<StoreEntity> -> List<StoresMainSearch> 
	public static List<StoresMainSearch> getList(List<StoreEntity> storeEntities) {
		List<StoresMainSearch> storesMainSearchs = new ArrayList<>();

		for (StoreEntity storeEntity: storeEntities) {
			StoresMainSearch storesMainSearch = new StoresMainSearch(storeEntity);
			storesMainSearchs.add(storesMainSearch);
		}

		return storesMainSearchs;
	}
}
