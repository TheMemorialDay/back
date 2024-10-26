package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# 전화번호 + 인증번호 확인 dto

@Getter
@Setter
@NoArgsConstructor
public class TelAuthCheckRequestDto {
	@NotBlank
	private String telNumber;
	@NotBlank
	private String telAuthNumber;
}