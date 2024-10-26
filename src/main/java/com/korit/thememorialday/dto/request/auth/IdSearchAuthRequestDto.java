package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# 아이디 찾기의 인증번호 확인 dto

@Getter
@Setter
@NoArgsConstructor
public class IdSearchAuthRequestDto {
	@NotBlank
	@Pattern(regexp="^[0-9]{11}$")
	private String telNumber;
	@NotBlank
	private String telAuthNumber;
}