package com.korit.thememorialday.common.object.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.korit.thememorialday.entity.support.QnAEntity;

import lombok.Getter;

@Getter
public class QnA {
    private Integer questionNumber;
    private String questionTitle;
    private String questionContents;
    private Date questionDay;
    private String userId;
    private String questionStatus;
    private String answerContents;

    private QnA(QnAEntity qnaEntity) {
        this.questionNumber = qnaEntity.getQuestionNumber();
        this.questionTitle = qnaEntity.getQuestionTitle();
        this.questionContents = qnaEntity.getQuestionContents();
        this.questionDay = qnaEntity.getQuestionDay();
        this.userId = qnaEntity.getUserId();
        this.questionStatus = qnaEntity.getQuestionStatus();
        this.answerContents = qnaEntity.getAnswerContents();
    }

    public static List<QnA> getList(List<QnAEntity> qnaEntities) {
        List<QnA> qnas = new ArrayList<>();
        for (QnAEntity qnaEntity : qnaEntities) {
            QnA qna = new QnA(qnaEntity);
            qnas.add(qna);
        }
        return qnas;
    }
}
