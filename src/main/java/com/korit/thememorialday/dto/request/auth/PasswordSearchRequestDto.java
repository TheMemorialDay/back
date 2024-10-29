package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# 비밀번호 찾기 요청 dto

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordSearchRequestDto {
	@NotBlank
	private String userId;
	@NotBlank
	@Pattern(regexp="^[0-9]{11}$")
	private String telNumber;
}

