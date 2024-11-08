package com.korit.thememorialday.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

//# 응답의 공통적 형태

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

	// % "static"
	public static ResponseEntity<ResponseDto> success() {
		ResponseDto responseBody = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(responseBody);
	}

	public static ResponseEntity<ResponseDto> validationFail() {
		ResponseDto responsBody = new ResponseDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsBody);
	}

	public static ResponseEntity<ResponseDto> duplicatedUserId() {
		ResponseDto responsBody = new ResponseDto(ResponseCode.DUPLICATED_USER_ID, ResponseMessage.DUPLICATED_USER_ID);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsBody);
	}

	public static ResponseEntity<ResponseDto> duplicatedaTelNumber() {
		ResponseDto responsBody = new ResponseDto(ResponseCode.DUPLICATED_TEL_NUMBER,
				ResponseMessage.DUPLICATED_TEL_NUMBER);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsBody);
	}

	public static ResponseEntity<ResponseDto> noExistUserId() {
		ResponseDto responsBody = new ResponseDto(ResponseCode.NO_EXIST_USER_ID, ResponseMessage.NO_EXIST_USER_ID);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsBody);
	}

	public static ResponseEntity<ResponseDto> noExistQuestion() {
		ResponseDto responsBody = new ResponseDto(ResponseCode.NO_EXIST_QUESTION,
				ResponseMessage.NO_EXIST_QUESTION);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsBody);
	}

	public static ResponseEntity<ResponseDto> noExistName() {
		ResponseDto responsBody = new ResponseDto(ResponseCode.NO_EXIST_NAME, ResponseMessage.NO_EXIST_NAME);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsBody);
	}

	public static ResponseEntity<ResponseDto> telAuthFail() {
		ResponseDto responsBody = new ResponseDto(ResponseCode.TEL_AUTH_FAIL, ResponseMessage.TEL_AUTH_FAIL);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responsBody);
	}

	public static ResponseEntity<ResponseDto> signInFail() {
		ResponseDto responsBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responsBody);
	}

	public static ResponseEntity<ResponseDto> noPermission() {
		ResponseDto responsBody = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responsBody);
	}

	public static ResponseEntity<ResponseDto> messageSendFail() {
		ResponseDto responseBody = new ResponseDto(ResponseCode.MESSAGE_SEND_FAIL, ResponseMessage.MESSAGE_SEND_FAIL);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
	}

	public static ResponseEntity<ResponseDto> tokenCreateFail() {
		ResponseDto responseBody = new ResponseDto(ResponseCode.TOKEN_CREATE_FAIL, ResponseMessage.TOKEN_CREATE_FAIL);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
	}

	public static ResponseEntity<ResponseDto> databaseError() {
		ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
	}

	// 없는 정보
	public static ResponseEntity<ResponseDto> noExistInfo() {
		ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_INFO, ResponseMessage.NO_EXIST_INFO);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
	}

	public static ResponseEntity<ResponseDto> noExistProduct() {
		ResponseDto responsBody = new ResponseDto(ResponseCode.NO_EXIST_PRODUCT, ResponseMessage.NO_EXIST_PRODUCT);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsBody);
	}

	public static ResponseEntity<ResponseDto> noExistStore() {
		ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_STORE, ResponseMessage.NO_EXIST_STORE);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	public static ResponseEntity<ResponseDto> noExistOrder() {
		ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_ORDER, ResponseMessage.NO_EXIST_ORDER);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	public static ResponseEntity<ResponseDto> noExistReview() {
		ResponseDto responsBody = new ResponseDto(ResponseCode.NO_EXIST_REVIEW,
				ResponseMessage.NO_EXIST_REVIEW);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsBody);
	}
}
