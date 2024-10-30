package com.korit.thememorialday.service.support;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.support.PostQnARequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.support.GetQnADetailResponseDto;
import com.korit.thememorialday.dto.response.support.GetQnAListResponseDto;

public interface QnAService {
    ResponseEntity<ResponseDto> postQnA(PostQnARequestDto dto);
    ResponseEntity<? super GetQnAListResponseDto> GetQnAList();
    ResponseEntity<? super GetQnADetailResponseDto> getQnADetail(Integer questionNumber);
    ResponseEntity<ResponseDto> deleteQnA(Integer questionNumber);
}
