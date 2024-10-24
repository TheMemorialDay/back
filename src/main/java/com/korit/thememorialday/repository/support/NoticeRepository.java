package com.korit.thememorialday.repository.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.thememorialday.entity.support.NoticeEntity;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer>{

    NoticeEntity findByNoticeNumber(Integer noticeNumber);
}
