package com.example.server.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "중복되는 이메일입니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");

    private final HttpStatus state;
    private final String message;

}
