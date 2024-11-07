package com.korit.thememorialday.entity.support;

import java.util.Date;

import com.korit.thememorialday.dto.request.support.PostQnARequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="question_answer")
@Table(name="question_answer")
public class QnAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionNumber;

    @Column(name = "question_title", length = 50, nullable = false)
    private String questionTitle;

    @Column(name = "question_contents", nullable = false, columnDefinition = "TEXT")
    private String questionContents;

    @Column(name = "question_day", nullable = false)
    private Date questionDay;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "question_status", length = 4, nullable = false)
    private String questionStatus;

    @Column(name = "answer_contents", nullable = true)
    private String answerContents;

    public QnAEntity(PostQnARequestDto dto) {
        this.questionTitle = dto.getQuestionTitle();
        this.questionContents = dto.getQuestionContents();
        this.userId = dto.getUserId();
        this.questionDay = dto.getQuestionDay();
        this.questionStatus = dto.getQuestionStatus();
        this.answerContents = dto.getAnswerContents();
    }
}
