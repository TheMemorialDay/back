package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.korit.thememorialday.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
  List<ProductEntity> findByStoreNumber(Integer storeNumber);
}
