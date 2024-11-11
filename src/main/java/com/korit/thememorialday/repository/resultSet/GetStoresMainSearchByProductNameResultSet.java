package com.korit.thememorialday.repository.resultSet;

//* stores main search - 상품명으로 검색 가게 불러오기

public interface GetStoresMainSearchByProductNameResultSet {
	Integer getStoreNumber();
	String getStoreImageUrl();
	String getStoreName();
	String getStoreGugun();
	String getStoreDong();
	Float getStoreRating();
	Integer getReviewCount();
	Integer getLikeCount();
}
