package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

//# 로그인 요청 dto

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {
	@NotBlank
	private String userId;
	@NotBlank
	private String password;
}
