package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.auth.IdCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchAuthRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchRequestDto;
import com.korit.thememorialday.dto.request.auth.SignInRequestDto;
import com.korit.thememorialday.dto.request.auth.SignUpRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.auth.GetSignInResponseDto;
import com.korit.thememorialday.dto.response.auth.IdSearchResponseDto;
import com.korit.thememorialday.dto.response.auth.SignInResponseDto;

public interface AuthService {
	// 아이디 중복 확인
	ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
	// 전화번호 확인
	ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto);
	// 전화번호 + 인증번호 확인
	ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto);
	// 회원가입
	ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
	// 로그인
	ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
	
	// 아이디 찾기 - 이름 & 전화번호 확인
	ResponseEntity<ResponseDto> beforeIdSearch(IdSearchRequestDto dto);
	// 아이디 찾기 - 전화번호 & 인증번호 확인
	ResponseEntity<? super IdSearchResponseDto> IdSearch(IdSearchAuthRequestDto dto);

	// zustand 현재 접속해 있는 유저와 일치하는지 확인하기 위함
	ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);
}