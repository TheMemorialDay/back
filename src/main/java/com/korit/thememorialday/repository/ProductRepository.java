package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    ProductEntity findByProductNumber(Integer productNumber);

    List<ProductEntity> findByStoreNumber(Integer storeNumber); // 특정 store_number에 해당하는 모든 상품 조회

    //* store main page - 상품명으로 가게 검색
    List<ProductEntity> findByProductNameContaining(String productName);    
    
}
