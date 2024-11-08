package com.korit.thememorialday.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.thememorialday.dto.response.ResponseCode;
import com.korit.thememorialday.dto.response.ResponseDto;
import com.korit.thememorialday.dto.response.ResponseMessage;
import com.korit.thememorialday.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetSignInResponseDto extends ResponseDto {

    private String userId;
    private String name;
    private String telNumber;
    private String permission;
    private Integer storeNumber;

    public GetSignInResponseDto(UserEntity userEntity, Integer storeNumber) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = userEntity.getUserId();
        this.name = userEntity.getName();
        this.telNumber = userEntity.getTelNumber();
        this.permission = userEntity.getPermission();
        this.storeNumber = storeNumber;
    }

    public static ResponseEntity<GetSignInResponseDto> success(UserEntity userEntity, Integer storeNumber) {
        GetSignInResponseDto responseBody = new GetSignInResponseDto(userEntity, storeNumber);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
