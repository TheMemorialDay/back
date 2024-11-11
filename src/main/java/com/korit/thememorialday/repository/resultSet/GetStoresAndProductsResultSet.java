package com.korit.thememorialday.repository.resultSet;

//# stores 엔터티에서 스토어명  
//# & products 엔터티에서 상품명으로 가게 검색 

public interface GetStoresAndProductsResultSet {
	String getStoreName();
	String getProductName();
}
