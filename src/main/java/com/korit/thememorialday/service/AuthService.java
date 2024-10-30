package com.korit.thememorialday.service;

import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.request.auth.IdCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchAuthRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchRequestDto;
import com.korit.thememorialday.dto.request.auth.PasswordAuthRequestDto;
import com.korit.thememorialday.dto.request.auth.PatchPasswordRequestDto;
import com.korit.thememorialday.dto.request.auth.PatchUserInfoRequestDto;
import com.korit.thememorialday.dto.request.auth.PasswordSearchRequestDto;
import com.korit.thememorialday.dto.request.auth.SignInRequestDto;
import com.korit.thememorialday.dto.request.auth.SignUpRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthRequestDto;
import com.korit.thememorialday.dto.request.auth.UserUpdatePasswordCheckRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;

import com.korit.thememorialday.dto.response.auth.GetSignInResponseDto;

import com.korit.thememorialday.dto.response.auth.GetUserInfoResponseDto;

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

	// 비밀번호 찾기 - 아이디 & 전화번호
	ResponseEntity<ResponseDto> passwordSearch(PasswordSearchRequestDto dto);
	// 비밀번호 찾기 - 전화번호 & 인증번호 확인
	ResponseEntity<ResponseDto> passwordAuthCheck(PasswordAuthRequestDto dto);
	// 비밀번호 재설정 - 아이디 & 전화번호
	ResponseEntity<ResponseDto> passwordResetting(PatchPasswordRequestDto dto);

	// 회원정보 수정 시 비밀번호 확인
	ResponseEntity<ResponseDto> userUpdatePasswordCheck(UserUpdatePasswordCheckRequestDto dto);

	// 회원 개인 정보 보기
	ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(String userId);
	// 회원 개인 정보 수정
	ResponseEntity<ResponseDto> patchUserInfo(PatchUserInfoRequestDto dto, String userId);

}