package com.korit.thememorialday.controller.support;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.service.support.NoticeService;
import com.korit.thememorialday.dto.response.support.GetNoticeListResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/support/notice")
@RequiredArgsConstructor
public class NoticeController {
    
    private final NoticeService noticeService;

    @GetMapping(value={"", "/"})
    public ResponseEntity<? super GetNoticeListResponseDto> getNoticeList() {
        ResponseEntity<? super GetNoticeListResponseDto> response = noticeService.GetNoticeList();
        return response;
    }
}
