package com.korit.thememorialday.controller.mypage_user_info;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.thememorialday.dto.request.auth.TelAuthRequestDto;
import com.korit.thememorialday.dto.request.mypage_user_info.PatchUserInfoRequestDto;
import com.korit.thememorialday.dto.request.mypage_user_info.UserUpdatePasswordCheckRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.mypage_user_info.GetUserInfoResponseDto;
import com.korit.thememorialday.dto.response.mypage_user_info.GetUserUpdateInfoResponseDto;
import com.korit.thememorialday.service.MypageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//# 마이페이지 개인정보

@RestController
@RequestMapping("/mypage/user-info")
@RequiredArgsConstructor
public class MypageController {

	private final MypageService mypageService;
	
	//* 개인정보 수정 시 비밀번호 확인 후 개인정보 불러오기
	@PostMapping("/password-check")
	public ResponseEntity<? super GetUserInfoResponseDto> userUpdateCheck(
		@RequestBody @Valid UserUpdatePasswordCheckRequestDto reqeustBody,
		@AuthenticationPrincipal String userId
	) {
		ResponseEntity<? super GetUserInfoResponseDto> response = mypageService.userUpdatePasswordCheck(reqeustBody, userId);
		return response;
	}

	// * 전화번호 인증
	@PostMapping("/tel-auth")
	public ResponseEntity<ResponseDto> userUpdateTelAuth(
			@RequestBody @Valid TelAuthRequestDto requestBody,
			@AuthenticationPrincipal String userId) {
		ResponseEntity<ResponseDto> response = mypageService.userUpdateTelAuth(requestBody, userId);
		return response;
	}

	// * 전화번호 + 인증번호 확인
	@PostMapping("/tel-auth-check")
	public ResponseEntity<ResponseDto> userUpdateTelAuthCheck(
			@RequestBody @Valid TelAuthCheckRequestDto requestBody,
			@AuthenticationPrincipal String userId) {
		ResponseEntity<ResponseDto> response = mypageService.userUpdateTelAuthCheck(requestBody, userId);
		return response;
	}

	// * 회원 개인 정보 수정
	@PatchMapping("/patch-info")
	public ResponseEntity<ResponseDto> patchUserInfo(
			@RequestBody @Valid PatchUserInfoRequestDto requestBody,
			@AuthenticationPrincipal String userId) {
		ResponseEntity<ResponseDto> response = mypageService.patchUserInfo(requestBody, userId);
		return response;
	};

	@GetMapping("/{userId}")
	public ResponseEntity<? super GetUserUpdateInfoResponseDto> getUpdateUserInfo(
		@PathVariable("userId") String userId
	) {
		ResponseEntity<? super GetUserUpdateInfoResponseDto> response = mypageService.getUpdateInfo(userId);
		return response;
	}
}
