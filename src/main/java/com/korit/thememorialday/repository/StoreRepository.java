package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.StoreEntity;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {

  StoreEntity findByStoreNumber(Integer storeNumber);

  List<StoreEntity> findByOrderByStoreNumberDesc();

}
