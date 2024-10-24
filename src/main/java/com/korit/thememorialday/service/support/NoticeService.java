package com.korit.thememorialday.service.support;

import org.springframework.http.ResponseEntity;


import com.korit.thememorialday.dto.response.support.GetNoticeListResponseDto;
import com.korit.thememorialday.dto.response.support.GetNoticeResponseDto;

public interface NoticeService {

    ResponseEntity<? super GetNoticeListResponseDto> GetNoticeList();
    ResponseEntity<? super GetNoticeResponseDto> getNotice(Integer noticeNumber);
}
