package com.korit.thememorialday.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.thememorialday.dto.request.PostPayMentRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.entity.PayMentEntity;
import com.korit.thememorialday.repository.PayMentRepository;
import com.korit.thememorialday.service.PayMentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PayMentServiceImplement implements PayMentService {

  private final PayMentRepository payMentRepository;

  @Override
  public ResponseEntity<ResponseDto> postPayMent(PostPayMentRequestDto dto) {

    try {

      PayMentEntity payMentEntity = new PayMentEntity(dto);
      payMentRepository.save(payMentEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success();
  }

}
