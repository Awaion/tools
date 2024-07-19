package com.awaion.demo026;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail error(RuntimeException exception) {
        // 错误对象 ProblemDetail,ErrorResponse
        return ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}

