package com.korit.thememorialday.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.auth.IdCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchTelNumberAuthRequestDto;
import com.korit.thememorialday.dto.request.auth.IdSearchNameTelNumberRequestDto;
import com.korit.thememorialday.dto.request.auth.PasswordAuthRequestDto;
import com.korit.thememorialday.dto.request.auth.PasswordResettingFinalRequestDto;
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
import com.korit.thememorialday.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PatchMapping;

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

	//* 아이디 찾기 시도 (이름 + 전화번호) (프론트에서 전송버튼 누를 시)
	@PostMapping("/id-search-first")
	public ResponseEntity<ResponseDto> beforeIdSearch(
		@RequestBody @Valid IdSearchNameTelNumberRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.idSearchNameTelCheck(requestBody);
		return response;
	}

	//* 아이디 찾기 (전화번호 + 인증번호) (프론트에서 확인버튼 누를 시)
	@PostMapping("/id-search-middle")
	public ResponseEntity<ResponseDto> idSearchtelAuthCheck(
		@RequestBody @Valid IdSearchTelNumberAuthRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.idSearchTelAuthCheck(requestBody);
		return response;
	}

	//* 최종 아이디 찾기 (프론트에서 아이디 찾기 버튼 누를 시)
	@PostMapping("/id-search-result")
	public ResponseEntity<? super IdSearchResponseDto> idSearch(
		@RequestBody @Valid IdSearchNameTelNumberRequestDto requestBody
	) {
		ResponseEntity<? super IdSearchResponseDto> response = authService.getIdSearch(requestBody);
		return response;
	}

	// 로그인 유저 정보 확인
	@GetMapping("/get-sign-in")
	public ResponseEntity<? super GetSignInResponseDto> getSignIn(
		@AuthenticationPrincipal String userId
	) {
		ResponseEntity<? super GetSignInResponseDto> response = authService.getSignIn(userId);
		return response;
	}

	//* 비밀번호 재설정 전 (아이디 + 전화번호)
	@PostMapping("/password-search")
	public ResponseEntity<ResponseDto> passwordSearch(
		@RequestBody @Valid PasswordResettingIdAndTelNumberRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.passwordResettingIdTelCheck(requestBody);
		return response;
	};

	//* 비밀번호 재설정 전 전화번호 + 인증번호 확인
	@PostMapping("/password-search-tel-auth-check")
	public ResponseEntity<ResponseDto> passwordAuthCheck(
		@RequestBody @Valid PasswordAuthRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.passwordResettingAuthCheck(requestBody);
		return response;
	};

	//* 비밀번호 재설정 들어가기 전 최종적으로 통합 확인
	@PostMapping("/password-search-final")
	public ResponseEntity<ResponseDto> passwordFinalCheck(
		@RequestBody @Valid PasswordResettingFinalRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.passwordResettingFinalCheck(requestBody);
		return response;
	};

	//* 비밀번호 재설정 (로그인 X)
	@PatchMapping("/password-resetting")
	public ResponseEntity<ResponseDto> passwordResetting(
		@RequestBody @Valid PasswordResettingRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.passwordResetting(requestBody);
		return response;
	};
	
}
