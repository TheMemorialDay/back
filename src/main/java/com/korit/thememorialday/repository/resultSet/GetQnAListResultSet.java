package com.korit.thememorialday.repository.resultSet;

import java.util.Date;

public interface GetQnAListResultSet {
    String getUserId();
    String getName();
    Integer getQuestionNumber();
    String getQuestionTitle();
    String getQuestionContents();
    Date getQuestionDay();
    String getQuestionStatus();
    String getAnswerContents();
} 
