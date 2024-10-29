package com.korit.thememorialday.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.UserEntity;

import lombok.Getter;

//# 개인정보 불러오기 응답 dto (회원정보 수정할 수 있는)

@Getter
public class GetUserInfoResponseDto extends ResponseDto {
	private String password;
	private String name;
	private String birth;
	private String gender;
	private String telNumber;
	// telAuthNumber는 tel_auth 테이블에 있기 때문에 resultSet 만들어야 함

	private GetUserInfoResponseDto(UserEntity userEntity) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.password = userEntity.getPassword();
		this.name = userEntity.getName();
		this.birth = userEntity.getBirth();
		this.gender = userEntity.getGender();
		this.telNumber = userEntity.getTelNumber();
	}

	public static ResponseEntity<? super GetUserInfoResponseDto> success(UserEntity userEntity) {
		GetUserInfoResponseDto responseBody = new GetUserInfoResponseDto(userEntity);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}
}
