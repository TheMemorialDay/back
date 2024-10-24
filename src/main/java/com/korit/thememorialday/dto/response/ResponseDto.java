package com.korit.thememorialday.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {
    private String code;
    private String message;

    public static ResponseEntity<ResponseDto> success() {
        ResponseDto responsdBody = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(responsdBody);
    }

    public static ResponseEntity<ResponseDto> noExistQuestion() {
        ResponseDto responsdBody = new ResponseDto(ResponseCode.NO_EXIST_QUESTION, ResponseMessage.NO_EXIST_QUESTION);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsdBody);
    }

    public static ResponseEntity<ResponseDto> databaseError() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
