package com.korit.thememorialday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.ReviewPhotoEntity;
import java.util.List;


@Repository
public interface ReviewPhotoRepository extends JpaRepository<ReviewPhotoEntity, Integer>{
    List<ReviewPhotoEntity> findByReviewNumber(Integer reviewNumber);
}
