package com.korit.thememorialday.repository;

import com.korit.thememorialday.entity.LikeEntity;
import com.korit.thememorialday.entity.pk.LikePk;
import com.korit.thememorialday.repository.resultSet.GetLikeUserResultSet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, LikePk> {

  // 찜기능
  LikeEntity findByUserIdAndStoreNumber(String userId, Integer storeNumber);

  // storeNumber 별 찜한 Store 목록
  List<LikeEntity> findByStoreNumber(Integer storeNumber);

  // userId 별 찜한 storeNumber
  List<LikeEntity> findByUserId(String userId);

  @Query(value = "SELECT " +
      "    L.user_id as userId, " +
      "    L.store_number as storeNumber, " +
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
      "    S.saturday_last as saturdayLast " +
      "FROM `like` L LEFT JOIN store S " +
      "ON L.store_number = S.store_number " +
      "WHERE L.user_id = :userId ", nativeQuery = true)
  List<GetLikeUserResultSet> getLikeStoreList(@Param("userId") String userId);

}
