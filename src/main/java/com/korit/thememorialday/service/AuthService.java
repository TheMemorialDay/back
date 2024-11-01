package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.auth.IdCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchTelNumberAuthRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchNameTelNumberRequestDto;
import com.korit.thememorialday.dto.request.auth.PasswordAuthRequestDto;
import com.korit.thememorialday.dto.request.auth.PasswordResettingRequestDto;
import com.korit.thememorialday.dto.request.auth.PasswordResettingIdAndTelNumberRequestDto;
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
	
	//* 아이디 찾기 */
	// 아이디 찾기 - 이름 & 전화번호 확인
	ResponseEntity<ResponseDto> idSearchNameTelCheck(IdSearchNameTelNumberRequestDto dto);
	// 아이디 찾기 - 전화번호 & 인증번호 확인
	ResponseEntity<ResponseDto> idSearchTelAuthCheck(IdSearchTelNumberAuthRequestDto dto);
	// 아이디 찾기 - 결과물 (로그인 x, 이름 전화번호로 일치하는 유저 가져오기 시도)
	ResponseEntity<? super IdSearchResponseDto> getIdSearch(IdSearchNameTelNumberRequestDto dto);


	// zustand 현재 접속해 있는 유저와 일치하는지 확인하기 위함
	ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);

	// 비밀번호 재설정 전 - 아이디 & 전화번호
	ResponseEntity<ResponseDto> passwordResettingIdTelCheck(PasswordResettingIdAndTelNumberRequestDto dto);

	// 비밀번호 재설정 전 - 전화번호 & 인증번호 확인
	ResponseEntity<ResponseDto> passwordResettingAuthCheck(PasswordAuthRequestDto dto);
	
	// 비밀번호 재설정
	ResponseEntity<ResponseDto> passwordResetting(PasswordResettingRequestDto dto);

}