package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.StoreEntity;
import com.korit.thememorialday.repository.resultSet.GetStoreOrderResultSet;
import com.korit.thememorialday.repository.resultSet.GetStoreResultSet;
import com.korit.thememorialday.repository.resultSet.GetStoresMainSearchByProductNameResultSet;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {

  StoreEntity findByStoreNumber(Integer storeNumber);

  List<StoreEntity> findByOrderByStoreNumberDesc();

  StoreEntity findByUserId(String userId);

  @Query(value = "SELECT " +
      "    S.store_number as storeNumber, " +
      "    S.store_name as storeName, " +
      "    S.store_introduce as storeIntroduce, " +
      "    S.store_particular as storeParticular, " +
      "    S.store_contact as storeContact, " +
      "    S.store_caution as storeCaution, " +
      "    S.store_address as storeAddress, " +
      "    S.store_detail_address as storeDetailAddress, " +
      "    S.store_gugun as storeGugun, " +
      "    S.store_dong as storeDong, " +
      "    S.store_latitude as storeLatitude, " +
      "    S.store_longtitude as storeLongtitude, " +
      "    S.store_tel as storeTel, " +
      "    S.store_image_url as storeImageUrl, " +
      "    S.sunday_open as sundayOpen, " +
      "    S.sunday_last as sundayLast, " +
      "    S.monday_open as mondayOpen, " +
      "    S.monday_last as mondayLast, " +
      "    S.tuesday_open as tuesdayOpen, " +
      "    S.tuesday_last as tuesdayLast, " +
      "    S.wednesday_open as wednesdayOpen, " +
      "    S.wednesday_last as wednesdayLast, " +
      "    S.thursday_open as thursdayOpen, " +
      "    S.thursday_last as thursdayLast, " +
      "    S.friday_open as fridayOpen, " +
      "    S.friday_last as frißdayLast, " +
      "    S.saturday_open as saturdayOpen, " +
      "    S.saturday_last as saturdayLast, " +
      "FROM store S LEFT JOIN user U " +
      "ON S.user_id = U.user_id " +
      "WHERE S.store_number = :storeNumber ", nativeQuery = true)
  GetStoreResultSet getStore(@Param("storeNumber") Integer storeNumber);

  @Query(value = "SELECT " +
      "    S.store_number as storeNumber, " +
      "    P.product_number as productNumber, " +
      "    P.product_name as productName, " +
      "    P.product_introduce as productIntroduce, " +
      "    P.product_price as productPrice, " +
      "    P.product_today as productToday, " +
      "    P.product_tag as productTag " +
      "FROM store S LEFT JOIN product P " +
      "ON S.store_number = P.store_number " +
      "WHERE S.store_number = :storeNumber", nativeQuery = true)
  GetStoreOrderResultSet getStoreOrderList(@Param("storeNumber") Integer storeNumber);

  //* store main search - 가게명 & 상품명 검색해서 가게 불러오기
  @Query(value = 
    "SELECT DISTINCT * FROM store " +
    "WHERE store_name LIKE %:storeName% " +
    "OR store_number IN ( " +
    "SELECT store_number FROM product " +
    "WHERE product_name LIKE %:productName%)", nativeQuery = true)
    List<StoreEntity> getStoreByMainSearch(
      @Param("storeName") String storeName,
      @Param("productName") String productName
      );
  
  //* store main day select - 픽업가능요일 선택해서 가게 불러오기
  // 월
  @Query(value = 
    "SELECT * FROM store " +
    "WHERE monday_open != '휴무일'", nativeQuery = true)
  List<StoreEntity> getStoreByOpenMonday();

  // 화
  @Query(value = 
    "SELECT * FROM store " +
    "WHERE tuesday_open != '휴무일'", nativeQuery = true)
  List<StoreEntity> getStoreByOpenTuesDay();

  // 수
  @Query(value = 
    "SELECT * FROM store " +
    "WHERE wednesday_open != '휴무일'", nativeQuery = true)
  List<StoreEntity> getStoreByOpenWednesDay();

  // 목
  @Query(value = 
    "SELECT * FROM store " +
    "WHERE thursday_open != '휴무일'", nativeQuery = true)
  List<StoreEntity> getStoreByOpenThursDay();

  // 금
  @Query(value = 
    "SELECT * FROM store " +
    "WHERE friday_open != '휴무일'", nativeQuery = true)
  List<StoreEntity> getStoreByOpenFriDay();

  // 토
  @Query(value = 
    "SELECT * FROM store " +
    "WHERE saturday_open != '휴무일'", nativeQuery = true)
  List<StoreEntity> getStoreByOpenSaturDay();

  // 일
  @Query(value = 
    "SELECT * FROM store " +
    "WHERE sunday_open != '휴무일'", nativeQuery = true)
  List<StoreEntity> getStoreByOpenSunDay();
}
