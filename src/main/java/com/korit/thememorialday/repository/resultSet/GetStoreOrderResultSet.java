package com.korit.thememorialday.repository.resultSet;

public interface GetStoreOrderResultSet {

  Integer getStoreNumber();
  // 상품 테이블 조인 참고 //

  String getProductNumber();

  String getProductName();

  String getProductIntroduce();

  String getProductPrice();

  String getProductToday();

  String getProductTag();
}
