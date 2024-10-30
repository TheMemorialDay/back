package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.ProductMappingEntity;

@Repository
public interface ProductMappingRepository extends JpaRepository<ProductMappingEntity, Integer> {

    List<ProductMappingEntity> findByProductNumber(Integer productNumber);
    
}
