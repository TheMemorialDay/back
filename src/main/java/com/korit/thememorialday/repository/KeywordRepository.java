package com.korit.thememorialday.repository;

import com.korit.thememorialday.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface KeywordRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "SELECT p.product_tag AS keyword, COUNT(*) AS frequency " +
            "FROM product p GROUP BY p.product_tag " +
            "ORDER BY frequency DESC LIMIT 10", nativeQuery = true)
    List<Map<String, Object>> findPopularKeywords();
}