package com.korit.thememorialday.repository.support;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.korit.thememorialday.entity.support.QnAEntity;

@Repository
public interface QnARepository extends JpaRepository<QnAEntity, Integer>{
    
    List<QnAEntity> findByOrderByQuestionNumberDesc();
    QnAEntity findByQuestionNumber(Integer questionNumber);
}
