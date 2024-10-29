package com.korit.thememorialday.service.join;

import org.springframework.http.ResponseEntity;


import com.korit.thememorialday.dto.request.join.PatchJoinRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;


public interface JoinService {

    // 아이디를 통해 회원을 확인하고 사업자 등록 정보 입력 받음
    ResponseEntity<ResponseDto> patchUserPermission(String userId, PatchJoinRequestDto dto);
}
