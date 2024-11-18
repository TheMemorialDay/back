package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.ProductOptionEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOptionEntity, Integer> {
    List<ProductOptionEntity> findByOptionNumber(Integer optionNumber);

    @Transactional
    void deleteByOptionNumber(Integer optionNumber);
}
