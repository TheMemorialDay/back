package com.korit.thememorialday.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.PostPayMentRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.service.PayMentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage/order-detail")
@RequiredArgsConstructor
public class PayMentController {

  private final PayMentService payMentService;

  @PostMapping(value = { "", "/" })
  public ResponseEntity<ResponseDto> posdtPayMent(
      @RequestBody @Valid PostPayMentRequestDto requestBody) {
    ResponseEntity<ResponseDto> response = payMentService.postPayMent(requestBody);
    return response;
  }

}
