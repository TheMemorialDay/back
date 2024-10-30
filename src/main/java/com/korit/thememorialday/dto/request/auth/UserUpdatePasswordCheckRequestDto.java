package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# 회원정보 수정 작업할 때 비밀번호 확인 요청 dto

@Getter
@Setter
@NoArgsConstructor
public class UserUpdatePasswordCheckRequestDto {
	@NotBlank
	@Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$")
	private String password;
}
