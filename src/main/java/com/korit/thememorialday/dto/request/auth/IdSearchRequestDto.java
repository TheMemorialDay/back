package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# 아이디 찾기 요청 dto

@Getter
@Setter
@NoArgsConstructor
public class IdSearchRequestDto {
	@NotBlank
	private String name;
	@NotBlank
	@Pattern(regexp="^[0-9]{11}$")
	private String telNumber;
}
