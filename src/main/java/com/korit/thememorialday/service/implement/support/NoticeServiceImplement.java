package com.korit.thememorialday.service.implement.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.support.GetNoticeListResponseDto;
import com.korit.thememorialday.dto.response.support.GetNoticeResponseDto;
import com.korit.thememorialday.entity.support.NoticeEntity;
import com.korit.thememorialday.repository.support.NoticeRepository;
import com.korit.thememorialday.service.support.NoticeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImplement implements NoticeService{

    private final NoticeRepository noticeRepository;

    @Override
    public ResponseEntity<? super GetNoticeListResponseDto> GetNoticeList() {
        List<NoticeEntity> noticeEntities = new ArrayList<>();

        try {
            noticeEntities = noticeRepository.findByOrderByNoticeNumberDesc();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetNoticeListResponseDto.success(noticeEntities);
    }

    @Override
    public ResponseEntity<? super GetNoticeResponseDto> getNotice(Integer noticeNumber) {
        NoticeEntity noticeEntity = new NoticeEntity();
        try {
            noticeEntity = noticeRepository.findByNoticeNumber(noticeNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetNoticeResponseDto.success(noticeEntity);
    }
}
