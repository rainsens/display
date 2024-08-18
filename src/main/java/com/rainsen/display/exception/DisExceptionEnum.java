package com.rainsen.display.exception;

public enum DisExceptionEnum {

    USERNAME_REQUIRED(10001, "Please provide a username"),
    USERNAME_EXISTS(10002, "Username exists"),
    EMAIL_EXISTS(10003, "User email exists"),
    INSERTION_FAILED(10004, "Insertion failed"),
    USERNAME_NOT_EXISTS(10005, "User does not exist"),
    PASSWORD_WRONG(10006, "Incorrect password"),
    UNAUTHORISED_ACCESS(10007, "Unauthorised access"),
    TOKEN_EXPIRED(10008, "Login status expired"),
    TOKEN_WRONG(10009, "Inccorect login status"),
    REQUESTED_PARAMS_ERROR(10010, "Requested parameters incorrect"),
    PROJECT_NAME_EXISTS(10011, "Project name exists"),
    UPDATE_FAILED(10012, "Update failed"),
    RECORD_NOT_EXISTS(10013, "Record does not exist"),
    DELETION_FAILED(10014, "Deletion failed"),
    SURVEY_NAME_EXISTS(10015, "Questionnaire exists"),

    MAKE_DIR_FAILED(10016, "Make file directory failed"),
    FILE_NAME_WONG(10017, "Please provide an valid file"),
    FILE_NOT_VALID(10018, "File upload failed"),
    FILE_UPLOAD_FAILED(10019, "File upload failed"),
    FILE_WRITTEN_FAILED(10020, "File is written failed"),
    INVALID_OPERATION(10021, "Invalid operation"),
    REPETITIVE_OPERATION(10022, "Repetitive operation"),

    SYSTEM_ERROR(99999, "System error");

    Integer code;

    String msg;

    DisExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
