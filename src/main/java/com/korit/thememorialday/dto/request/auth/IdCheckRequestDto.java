package com.korit.thememorialday.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//# 아이디 찾기 dto

@Getter
@Setter
@NoArgsConstructor
public class IdCheckRequestDto {
	@NotBlank
	private String userId;
}
