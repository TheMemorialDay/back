package com.korit.thememorialday.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.UserEntity;

import lombok.Getter;

//# 아이디 찾기 응답 dto

@Getter
public class IdSearchResponseDto extends ResponseDto {
	private String name;
	private String telNumber;
	private String userId;

	private IdSearchResponseDto(UserEntity userEntity) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.name = userEntity.getName();
		this.telNumber = userEntity.getTelNumber();
		this.userId = userEntity.getUserId();
	}

	public static ResponseEntity<IdSearchResponseDto> success(UserEntity userEntity) {
		IdSearchResponseDto responseBody = new IdSearchResponseDto(userEntity);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}
}