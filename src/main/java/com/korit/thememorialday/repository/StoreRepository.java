package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.StoreEntity;
import com.korit.thememorialday.repository.resultSet.GetStoreOrderResultSet;
import com.korit.thememorialday.repository.resultSet.GetStoreResultSet;

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
      "    S.friday_last as fridayLast, " +
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

  @Query(value = "SELECT " +
      "S.store_number AS storeNumber, " +
      "S.store_name AS storeName " + 
      "FROM store S " +
      "WHERE S.store_number = :storeNumber", nativeQuery = true)
  String findStoreNameByStoreNumber(@Param("storeNumber") Integer storeNumber);

}
