package com.rainsen.display.exception;

import com.rainsen.display.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        logger.error("DefaultException", e);
        return ApiResponse.error(DisExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(DisException.class)
    @ResponseBody
    public Object handleDisplayException(DisException e) {
        logger.error("DisplayException", e);
        return ApiResponse.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResponse<Void> handleMethodArgumentException(MethodArgumentNotValidException e) {
        logger.error("MethodArgumentNotValidException: ", e);
        return getBindingResult(e.getBindingResult());
    }

    private ApiResponse<Void> getBindingResult(BindingResult result) {
        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                String fieldName = "";
                String message = error.getDefaultMessage();
                if (error instanceof FieldError) {
                    fieldName = ((FieldError) error).getField();
                }
                list.add(fieldName + ": " + message);
            }
        }
        if (list.isEmpty()) {
            return ApiResponse.error(DisExceptionEnum.REQUESTED_PARAMS_ERROR);
        }
        return ApiResponse.error(
                DisExceptionEnum.REQUESTED_PARAMS_ERROR.getCode(),
                list.toString()
        );
    }
}