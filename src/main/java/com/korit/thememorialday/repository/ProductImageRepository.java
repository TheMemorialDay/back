package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.ProductImageEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Integer> {
    List<ProductImageEntity> findByProductNumber(Integer productNumber);

    @Transactional
    void deleteByProductNumber(Integer prodcutNumber);
}
