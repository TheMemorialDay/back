package com.korit.thememorialday.dto.response.mypage_user_info;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;

import lombok.Getter;

//# 회원정보 수정을 위한 비밀번호 확인 response dto

@Getter
public class UserUpdatePasswordCheckResponseDto extends ResponseDto {
	private String accessToken;
	private Integer expiration;

	private UserUpdatePasswordCheckResponseDto(String accessToken) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.accessToken = accessToken;
		this.expiration = 10 * 60 * 60;
	}

	public static ResponseEntity<UserUpdatePasswordCheckResponseDto> success(String accessToken) {
		UserUpdatePasswordCheckResponseDto responseBody = new UserUpdatePasswordCheckResponseDto(accessToken);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}
}
