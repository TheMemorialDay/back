package com.korit.thememorialday.dto.response.support;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.common.object.support.Notice;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.support.NoticeEntity;

import lombok.Getter;

@Getter
public class GetNoticeListResponseDto extends ResponseDto{
    
    private List<Notice> notices;

    private GetNoticeListResponseDto(List<NoticeEntity> noticeEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.notices = Notice.getList(noticeEntities);
    }

    public static ResponseEntity<GetNoticeListResponseDto> success(List<NoticeEntity> noticeEntities) {
        GetNoticeListResponseDto responseBody = new GetNoticeListResponseDto(noticeEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}