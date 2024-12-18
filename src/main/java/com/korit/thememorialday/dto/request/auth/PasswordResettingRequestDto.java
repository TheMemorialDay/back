package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# 비밀번호 재설정 요청 dto

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResettingRequestDto {
	@NotBlank
	private String userId;
	@NotBlank
	private String telNumber;
	@NotBlank
	private String telAuthNumber;
	@NotBlank
	@Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$")
	private String password;
}
