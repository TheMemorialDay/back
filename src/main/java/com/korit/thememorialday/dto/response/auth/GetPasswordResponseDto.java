package com.korit.thememorialday.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.UserEntity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

//# 비밀번호만 가져오기 위한 응답

@Getter
public class GetPasswordResponseDto extends ResponseDto {

	@NotBlank
	private String password;

	private GetPasswordResponseDto(UserEntity userEntity) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.password = userEntity.getPassword();
	}

	public static ResponseEntity<GetPasswordResponseDto> success(UserEntity userEntity) {
		GetPasswordResponseDto responseBody = new GetPasswordResponseDto(userEntity);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}
}
