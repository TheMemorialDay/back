package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//* 비밀번호 재설정 전 아이디, 전화번호, 인증번호 최종 확인 요청 dto */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResettingFinalRequestDto {
	@NotBlank
	private String userId;
	@NotBlank
	@Pattern(regexp="^[0-9]{11}$")
	private String telNumber;
	@NotBlank
	private String telAuthNumber;
}
