package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# 회원가입 요청 dto

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
	@NotBlank
	private String userId;
	@NotBlank
	@Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$")
	private String password;
	@NotBlank
	private String name;
	@NotBlank
	private String birth;
	@NotBlank
	private String gender;
	@NotBlank
	@Pattern(regexp="^[0-9]{11}$")
	private String telNumber;
	@NotBlank
	private String telAuthNumber;
	@NotBlank
	@Pattern(regexp="^(home|naver|kakao|google)$")
	private String joinPath;
	private String snsId;
}
