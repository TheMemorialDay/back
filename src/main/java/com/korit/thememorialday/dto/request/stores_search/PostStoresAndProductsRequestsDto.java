package com.korit.thememorialday.dto.request.stores_search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# stores 페이지에서 가게명 & 상품명으로 가게 검색을 하기 위한 dto

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostStoresAndProductsRequestsDto {
	private String storeName;
	private String productName;
}
