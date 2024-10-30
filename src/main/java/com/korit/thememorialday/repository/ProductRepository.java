package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    ProductEntity findByProductNumber(Integer productNumber);

    // List<ProductEntity> findByStoreNumber(Integer storeNumber); // 특정 store_number에 해당하는 모든 상품 조회

    @Query(
        value = 
            "SELECT p " +
            "FROM ProductEntity p " +
            "JOIN p.store s " +
            "WHERE s.user.userId = :userId"
    )
    List<ProductEntity> findByStoreUserId(@Param("userId") String userId);
    
    
}
