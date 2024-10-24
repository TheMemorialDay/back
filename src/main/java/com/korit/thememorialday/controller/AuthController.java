package com.korit.thememorialday.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.auth.IdCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.SignInRequestDto;
import com.korit.thememorialday.dto.request.auth.SignUpRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.SignInResponseDto;
import com.korit.thememorialday.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	//* 아이디 중복 확인
	@PostMapping("/id-check")
	public ResponseEntity<ResponseDto> idCheck(
		@RequestBody @Valid IdCheckRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.idCheck(requestBody);
		return response;
	}

	//* 전화번호 인증
	@PostMapping("/tel-auth")
	public ResponseEntity<ResponseDto> telAuth(
		@RequestBody @Valid TelAuthRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.telAuth(requestBody);
		return response;
	}

	//* 전화번호 + 인증번호 확인
	@PostMapping("/tel-auth-check")
	public ResponseEntity<ResponseDto> telAuthCheck(
		@RequestBody @Valid TelAuthCheckRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.telAuthCheck(requestBody);
		return response;
	}

	//* 회원가입
	@PostMapping("/sign-up")
	public ResponseEntity<ResponseDto> signUp(
		@RequestBody @Valid SignUpRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.signUp(requestBody);
		return response;
	}

	//* 로그인
	@PostMapping("/sign-in")
	public ResponseEntity<? super SignInResponseDto> signIn(
		@RequestBody @Valid SignInRequestDto requestBody
	) {
		ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
		return response;
	}
	
}
