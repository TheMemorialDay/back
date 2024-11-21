package com.korit.thememorialday.repository.support;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.korit.thememorialday.entity.support.QnAEntity;

@Repository
public interface QnARepository extends JpaRepository<QnAEntity, Integer> {

    QnAEntity findByQuestionNumber(Integer questionNumber);

    // @Query(value =
    // "SELECT " +
    // "u.user_id AS userId, " +
    // "u.name AS name, " +
    // "qa.question_number AS questionNumber, " +
    // "qa.question_title AS questionTitle, " +
    // "qa.question_contents AS questionContents, " +
    // "qa.question_day AS questionDay, " +
    // "qa.question_status AS questionStatus, " +
    // "qa.answer_contents AS answerContents " +
    // "FROM TheMemorialDayDB.user AS u " +
    // "JOIN TheMemorialDayDB.question_answer AS qa " +
    // "ON u.user_id = qa.user_id " +
    // "ORDER BY qa.question_number DESC",
    // nativeQuery = true
    // )List<GetQnAListResultSet> getQnAList();

    // List<NoticeEntity> findByOrderByNoticeNumberDesc();
    List<QnAEntity> findByOrderByQuestionNumber();
}
