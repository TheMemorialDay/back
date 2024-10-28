package com.korit.thememorialday.dto.response;

//# 응답 코드 메세지 상수

public interface ResponseMessage {
	String SUCCESS = "Success.";

	String VALIDATION_FAIL = "Validation failed.";
	String DUPLICATED_USER_ID = "Duplicated user id.";
	String DUPLICATED_TEL_NUMBER = "Duplicated user tel number.";
	String NO_EXIST_USER_ID = "No exist user id.";
	String NO_EXIST_INFO = "No exist info.";
	String NO_EXIST_QUESTION = "no exist question.";

	String TEL_AUTH_FAIL = "Tel number authentication failed.";
	String SIGN_IN_FAIL = "Sign in failed.";
	String AUTHENTICATION_FAIL = "Authentication fail.";

	String NO_PERMISSION = "No permission.";

	String MESSAGE_SEND_FAIL = "Auth number send failed.";
	String TOKEN_CREATE_FAIL = "Token creation failed.";
	String DATABASE_ERROR = "Database error.";
}
