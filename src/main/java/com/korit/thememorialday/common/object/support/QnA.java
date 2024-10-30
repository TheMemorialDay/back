package com.korit.thememorialday.common.object.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.korit.thememorialday.repository.resultSet.GetQnAListResultSet;

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
    private String name;

    private QnA(GetQnAListResultSet resultSet) {
        this.questionNumber = resultSet.getQuestionNumber();
        this.questionTitle = resultSet.getQuestionTitle();
        this.questionContents = resultSet.getQuestionContents();
        this.questionDay = resultSet.getQuestionDay();
        this.userId = resultSet.getUserId();
        this.questionStatus = resultSet.getQuestionStatus();
        this.answerContents = resultSet.getAnswerContents();
        this.name = resultSet.getName();
    }

    public static List<QnA> getList(List<GetQnAListResultSet> resultSets) {
        List<QnA> qnas = new ArrayList<>();
        for (GetQnAListResultSet resultSet : resultSets) {
            QnA qna = new QnA(resultSet);
            qnas.add(qna);
        }
        return qnas;
    }
}
