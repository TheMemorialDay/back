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

    private QnA(QnAEntity resultSet) {
        this.questionNumber = resultSet.getQuestionNumber();
        this.questionTitle = resultSet.getQuestionTitle();
        this.questionContents = resultSet.getQuestionContents();
        this.questionDay = resultSet.getQuestionDay();
        this.userId = resultSet.getUserId();
        this.questionStatus = resultSet.getQuestionStatus();
        this.answerContents = resultSet.getAnswerContents();
    }

    public static List<QnA> getList(List<QnAEntity> resultSets) {
        List<QnA> qnas = new ArrayList<>();
        for (QnAEntity resultSet : resultSets) {
            QnA qna = new QnA(resultSet);
            qnas.add(qna);
        }
        return qnas;
    }
}
