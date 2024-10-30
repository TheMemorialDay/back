package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.mypage_user_info.PatchUserInfoRequestDto;
import com.korit.thememorialday.dto.request.mypage_user_info.UserUpdatePasswordCheckRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.mypage_user_info.GetUserInfoResponseDto;

//# 마이페이지 개인정보 서비스

public interface MypageService {

	// 회원정보 수정 시 비밀번호 확인
	ResponseEntity<? super GetUserInfoResponseDto> userUpdatePasswordCheck(UserUpdatePasswordCheckRequestDto dto, String userId);
	
	// 회원 개인 정보 보기
	// ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(String userId);
	// 회원 개인 정보 수정
	ResponseEntity<ResponseDto> patchUserInfo(PatchUserInfoRequestDto dto, String userId);

}