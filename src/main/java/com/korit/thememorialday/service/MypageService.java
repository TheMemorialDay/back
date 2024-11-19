package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthRequestDto;
import com.korit.thememorialday.dto.request.mypage_user_info.PatchUserInfoRequestDto;
import com.korit.thememorialday.dto.request.mypage_user_info.UserUpdatePasswordCheckRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.mypage_user_info.GetUserInfoResponseDto;
import com.korit.thememorialday.dto.response.mypage_user_info.GetUserUpdateInfoResponseDto;

//# 마이페이지 개인정보 서비스

public interface MypageService {

	// 회원정보 수정 시 비밀번호 확인
	ResponseEntity<? super GetUserInfoResponseDto> userUpdatePasswordCheck(UserUpdatePasswordCheckRequestDto dto, String userId);
	
	// 회원 개인 정보 수정 전 전화번호 인증
	ResponseEntity<ResponseDto> userUpdateTelAuth(TelAuthRequestDto dto, String userId);
	// 회원 개인 정보 수정 전 전화번호 + 인증번호 확인
	ResponseEntity<ResponseDto> userUpdateTelAuthCheck(TelAuthCheckRequestDto dto, String userId);

	// 회원 개인 정보 수정
	ResponseEntity<ResponseDto> patchUserInfo(PatchUserInfoRequestDto dto, String userId);

	ResponseEntity<? super GetUserUpdateInfoResponseDto> getUpdateInfo(String userId);

}
