package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.PostPayMentRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;

public interface PayMentService {

  ResponseEntity<ResponseDto> postPayMent(PostPayMentRequestDto dto);
}
