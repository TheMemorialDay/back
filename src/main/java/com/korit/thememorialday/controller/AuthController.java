package com.korit.thememorialday.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.korit.thememorialday.dto.response.auth.GetUserInfoResponseDto;
import com.korit.thememorialday.dto.response.auth.IdSearchResponseDto;
import com.korit.thememorialday.dto.response.auth.SignInResponseDto;
import com.korit.thememorialday.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	//* 아이디 찾기 시도
	@PostMapping("/id-search")
	public ResponseEntity<ResponseDto> beforeIdSearch(
		@RequestBody @Valid IdSearchRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.beforeIdSearch(requestBody);
		return response;
	}

	//* 최종 아이디 찾기
	@PostMapping("/id-search-tel-auth-check")
	public ResponseEntity<? super IdSearchResponseDto> idSearch(
		@RequestBody @Valid IdSearchAuthRequestDto requestBody
	) {
		ResponseEntity<? super IdSearchResponseDto> response = authService.IdSearch(requestBody);
		return response;
	}

	//* 비밀번호 찾기
	@PostMapping("/password-search")
	public ResponseEntity<ResponseDto> passwordSearch(
		@RequestBody @Valid PasswordSearchRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.passwordSearch(requestBody);
		return response;
	};

	//* 비밀번호 찾기 시 인증 확인
	@PostMapping("/password-search-tel-auth-check")
	public ResponseEntity<ResponseDto> passwordAuthCheck(
		@RequestBody @Valid PasswordAuthRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.passwordAuthCheck(requestBody);
		return response;
	};

	//* 비밀번호 재설정
	@PatchMapping("/password-resetting")
	public ResponseEntity<ResponseDto> passwordResetting(
		@RequestBody @Valid PatchPasswordRequestDto requestBody
	) {
		ResponseEntity<ResponseDto> response = authService.patchPassword(requestBody, null);
		return response;
	};

	//* 회원정보 수정 시 비밀번호 확인
	@PostMapping("/password-check")
	public ResponseEntity<ResponseDto> userUpdatePasswordCheck(
		@RequestBody @Valid UserUpdatePasswordCheckRequestDto reqeustBody
	) {
		ResponseEntity<ResponseDto> response = authService.userUpdatePasswordCheck(reqeustBody);
		return response;
	};

	//* 회원 개인 정보 보기
	@GetMapping("/{userId}")
	public ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(
		@PathVariable("userId") String userId
	) {
		ResponseEntity<? super GetUserInfoResponseDto> response = authService.getUserInfo(userId);
		return response;
	};

	//* 회원 개인 정보 수정
	@PostMapping("/{userId}")
	public ResponseEntity<ResponseDto> patchUserInfo(
		@RequestBody @Valid PatchUserInfoRequestDto requestBody,
		@PathVariable("userId") String userId
	) {
		ResponseEntity<ResponseDto> response = authService.patchUserInfo(requestBody, userId);
		return response;
	};
	
}
