package com.plusweek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviceController {
    @ExceptionHandler(CustomException.class) // 특정 예외 클래스가 발생하였을 때 실행될 메서드 지정
    public ResponseEntity<ExceptionResponse> handlerCustomException(CustomException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getErrorCode());
        return ResponseEntity.status(exceptionResponse.getHttpStatus()).body(exceptionResponse);
    }

    // validation 관련 오류 메시지
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        // Customize the error response without the detailed stack trace
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
