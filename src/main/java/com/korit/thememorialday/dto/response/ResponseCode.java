package com.korit.thememorialday.dto.response;

public interface ResponseCode {
    String SUCCESS = "SU";
    
    String VALIDATION_FAIL = "VF";
    String DUPLICATE_USER_ID = "DI";
    String DUPLICATE_TEL_NUMBER = "DT";
    String NO_EXIST_USER_ID = "NI";
    String NO_EXIST_TOOL = "NT";
    String NO_EXIST_QUESTION = "NQ";
    String NO_EXIST_CUSTOMER = "NC";
    String TOOL_INSUFFICIENT = "TI";

    String TEL_AUTH_FAIL = "TAF";
    String SIGN_IN_FAIL = "SF";
    String AUTHENTICATION_FAIL = "AF";

    String NO_PERMISSION = "MT";

    String DATABASE_ERROR = "DBE";
    String MESSAGE_SEND_FAIL = "TF";
    String TOKEN_CREATE_FAIL = "TCF";
}
