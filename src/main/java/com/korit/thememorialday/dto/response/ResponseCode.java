package com.korit.thememorialday.dto.response;

// ResponseDto의 code 상수

public interface ResponseCode {

  String SUCCESS = "SU";

  String VALIDATION_FAIL = "VF";
  String DUPLICATED_USER_ID = "DI";
  String DUPLICATED_TEL_NUMBER = "DT";
  String NO_EXIST_USER_ID = "NI";
  String NO_EXIST_NAME = "NN";
  String NO_EXIST_INFO = "NF";
  String NO_EXIST_QUESTION = "NQ";
  String NO_EXIST_PRODUCT = "NP";
  String NO_EXIST_STORE = "NS";
  String NO_EXIST_ORDER = "NO";
  String NO_EXIST_REVIEW = "NR";

  String TEL_AUTH_FAIL = "TAF";
  String SIGN_IN_FAIL = "SF";
  String AUTHENTICATION_FAIL = "AF";

  String NO_PERMISSION = "NP";

  String MESSAGE_SEND_FAIL = "TF";
  String TOKEN_CREATE_FAIL = "TCF";
  String DATABASE_ERROR = "DBE";

}
