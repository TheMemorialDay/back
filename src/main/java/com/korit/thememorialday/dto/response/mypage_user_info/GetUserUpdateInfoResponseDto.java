package com.korit.thememorialday.dto.response.mypage_user_info;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserUpdateInfoResponseDto extends ResponseDto{
    private String birth;
    private String gender;
    private String name;
    private String telNumber;

    private GetUserUpdateInfoResponseDto(UserEntity userEntity) {
		super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		this.birth = userEntity.getBirth();
		this.gender = userEntity.getGender();
        this.name = userEntity.getName();
        this.telNumber = userEntity.getTelNumber();
	}

	public static ResponseEntity<? super GetUserUpdateInfoResponseDto> success(UserEntity userEntity) {
		GetUserUpdateInfoResponseDto responseBody = new GetUserUpdateInfoResponseDto(userEntity);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}
}
