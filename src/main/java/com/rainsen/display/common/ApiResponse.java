package com.rainsen.display.common;

import com.rainsen.display.exception.DisExceptionEnum;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;

public class ApiResponse<T> {

    private Integer state;
    private String msg;
    private T data;

    private static final int OK_CODE = 10000;
    private static final String OK_MSG = "SUCCESS";

    public ApiResponse(Integer state, String msg, T data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public ApiResponse(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public ApiResponse() {
        this(OK_CODE, OK_MSG);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setData(data);
        return response;
    }

    public static <T> ApiResponse<T> error(Integer code, String msg) {
        return new ApiResponse<>(code, msg);
    }

    public static <T> ApiResponse<T> error(DisExceptionEnum e) {
        return new ApiResponse<>(e.getCode(), e.getMsg());
    }

    public static void errorOut(ServletResponse response, DisExceptionEnum e) {
        PrintWriter out;
        try {
            out = new HttpServletResponseWrapper((HttpServletResponse) response).getWriter();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        out.write("""
            {
                "state": %s,
                "msg": "%s",
                "data": null
            }
            """.formatted(e.getCode(), e.getMsg())
        );
        out.flush();
        out.close();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "state=" + state +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
