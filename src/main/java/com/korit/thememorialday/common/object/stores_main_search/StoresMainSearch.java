package com.korit.thememorialday.common.object.stores_main_search;

import java.util.ArrayList;
import java.util.List;

import com.korit.thememorialday.entity.StoreEntity;
import com.korit.thememorialday.repository.resultSet.GetStoresMainSearchByProductNameResultSet;

import lombok.Getter;

//# store main page에서 가게명 검색 - 가게 리스트 객체

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

	private StoresMainSearch(StoreEntity storeEntity) {
		this.storeNumber = storeEntity.getStoreNumber();
		this.storeImageUrl = storeEntity.getStoreImageUrl();
		this.storeName = storeEntity.getStoreName();
		this.storeGugun = storeEntity.getStoreGugun();
		this.storeDong = storeEntity.getStoreDong();
		this.storeRating = storeEntity.getStoreRating();
		this.reviewCount = storeEntity.getReviewCount();
		this.likeCount = storeEntity.getLikeCount();
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