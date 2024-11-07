package com.korit.thememorialday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.korit.thememorialday.entity.ThemaEntity;
import com.korit.thememorialday.entity.pk.ThemaPk;

import jakarta.transaction.Transactional;

public interface ThemaRepostiroy extends JpaRepository<ThemaEntity, ThemaPk> {

    List<ThemaEntity> findByProductNumber(Integer productNubmer);

    // List<ThemaEntity> findByStoreNumber(Integer StoreNumber);

    @Transactional
    void deleteByProductNumber(Integer productNumber);
}
