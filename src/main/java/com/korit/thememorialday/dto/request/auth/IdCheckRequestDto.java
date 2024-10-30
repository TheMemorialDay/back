package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# 아이디 중복 확인 dto

@Getter
@Setter
@NoArgsConstructor
public class IdCheckRequestDto {
	@NotBlank
	private String userId;
}
