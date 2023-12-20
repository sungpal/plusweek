package com.plusweek.plusweek.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    CONFLICT_PASSWORD_INCLUDED_IN_NICKNAME(HttpStatus.CONFLICT, "비밀번호에 사용자 닉네임이 포함될 수 없습니다"),

    CONFLICT_NICKNAME_IN_USE(HttpStatus.CONFLICT, "중복된 닉네임입니다"),

    CONFLICT_PASSWORD_NOT_CORRECT(HttpStatus.CONFLICT, "비밀번호가 일치하지 않습니다");

    private final HttpStatus httpStatus;
    private final String message;

}
