package com.korit.thememorialday.controller.mypage_user_info;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.thememorialday.dto.request.mypage_user_info.PatchUserInfoRequestDto;
import com.korit.thememorialday.dto.request.mypage_user_info.UserUpdatePasswordCheckRequestDto;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.mypage_user_info.GetUserInfoResponseDto;
import com.korit.thememorialday.service.MypageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//# 마이페이지 개인정보

@RestController
@RequestMapping("/mypage/userInfo")
@RequiredArgsConstructor
public class MypageController {

	private final MypageService mypageService;
	
	//* 개인정보 수정 시 비밀번호 확인
	@PostMapping("/password-check/{userId}")
	public ResponseEntity<? super GetUserInfoResponseDto> userUpdateCheck(
		@RequestBody @Valid UserUpdatePasswordCheckRequestDto reqeustBody,
		@PathVariable("userId") String userId
	) {
		ResponseEntity<? super GetUserInfoResponseDto> response = mypageService.userUpdatePasswordCheck(reqeustBody, userId);
		return response;
	}

	//* 회원 개인 정보 보기
	// @GetMapping("/{userId}")
	// public ResponseEntity<? super GetUserInfoResponseDto> getUserInfo(
	// 	@PathVariable("userId") String userId
	// ) {
	// 	ResponseEntity<? super GetUserInfoResponseDto> response = mypageService.getUserInfo(userId);
	// 	return response;
	// };

	//* 회원 개인 정보 수정
	@PostMapping("/{userId}")
	public ResponseEntity<ResponseDto> patchUserInfo(
		@RequestBody @Valid PatchUserInfoRequestDto requestBody,
		@PathVariable("userId") String userId
	) {
		ResponseEntity<ResponseDto> response = mypageService.patchUserInfo(requestBody, userId);
		return response;
	};

}
