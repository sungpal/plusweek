package com.plusweek.plusweek.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviceController {
    @ExceptionHandler(CustomException.class) // 특정 예외 클래스가 발생하였을 때 실행될 메서드 지정
    public ResponseEntity<ExceptionResponse> handlerCustomException(CustomException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getErrorCode());
        return ResponseEntity.status(exceptionResponse.getHttpStatus()).body(exceptionResponse);
    }
}
