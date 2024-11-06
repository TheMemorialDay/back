package com.korit.thememorialday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.PayMentEntity;

@Repository
public interface PayMentRepository extends JpaRepository<PayMentEntity, String> {

}
