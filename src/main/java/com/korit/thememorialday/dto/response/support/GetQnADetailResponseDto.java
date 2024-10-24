package com.korit.thememorialday.dto.response.support;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.support.QnAEntity;

import lombok.Getter;

@Getter
public class GetQnADetailResponseDto extends ResponseDto{
    private String questionTitle;
    private String questionContents;
    private Date questionDay;
    private String userId;
    private String questionStatus;
    private String answerContents;

    private GetQnADetailResponseDto(QnAEntity qnAEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.questionTitle = qnAEntity.getQuestionTitle();
        this.userId = qnAEntity.getUserId();
        this.questionDay = qnAEntity.getQuestionDay();
        this.questionContents = qnAEntity.getQuestionContents();
        this.questionStatus = qnAEntity.getQuestionStatus();
        this.answerContents = qnAEntity.getAnswerContents();
    }

    public static ResponseEntity<GetQnADetailResponseDto> success(QnAEntity qnAEntity) {
        GetQnADetailResponseDto responseBody = new GetQnADetailResponseDto(qnAEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
