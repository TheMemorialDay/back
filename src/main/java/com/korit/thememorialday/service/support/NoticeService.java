package com.korit.thememorialday.service.support;

import org.springframework.http.ResponseEntity;


import com.korit.thememorialday.dto.response.support.GetNoticeListResponseDto;

public interface NoticeService {

    ResponseEntity<? super GetNoticeListResponseDto> GetNoticeList();
    
}
