package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# 회원 개인 정보 수정 요청 dto

@Getter
@Setter
@NoArgsConstructor
public class PatchUserInfoRequestDto {
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
}
