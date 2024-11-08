package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer>{
    List<ReviewEntity> findByUserIdOrderByReviewDayDesc(String userId);
    List<ReviewEntity> findByStoreNameOrderByReviewDayDesc(String storeName);
}
