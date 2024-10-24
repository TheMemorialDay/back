package com.korit.thememorialday.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.korit.thememorialday.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    // 인티저로만 써도 되는지 뤼튼한테 물어보기
}
