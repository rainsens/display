package com.rainsen.display.exception;

public class DisException extends RuntimeException {
    private final Integer code;
    private final String msg;

    public DisException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public DisException(DisExceptionEnum dee) {
        this(dee.getCode(), dee.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
