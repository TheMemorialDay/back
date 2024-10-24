package com.korit.thememorialday.dto.response.support;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.support.NoticeEntity;

import lombok.Getter;

@Getter
public class GetNoticeResponseDto extends ResponseDto{
    private Integer noticeNumber;
    private String noticeTitle;
    private Date noticeDay;
    private String noticeContents;

    private GetNoticeResponseDto(NoticeEntity noticeEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.noticeNumber = noticeEntity.getNoticeNumber();
        this.noticeTitle = noticeEntity.getNoticeTitle();
        this.noticeDay = noticeEntity.getNoticeDay();
        this.noticeContents = noticeEntity.getNoticeContents();
    }

    public static ResponseEntity<GetNoticeResponseDto> success(NoticeEntity noticeEntity) {
        GetNoticeResponseDto responseBody = new GetNoticeResponseDto(noticeEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
