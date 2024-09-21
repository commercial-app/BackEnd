package com.example.server.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    NOT_FOUND_BOARD(HttpStatus.NOT_FOUND, "존재하지 않는 보드입니다."),
    NOT_FOUND_MISSION(HttpStatus.NOT_FOUND, "존재하지 않는 미션입니다."),
    NOT_FOUND_MISSION_SUMMIT(HttpStatus.NOT_FOUND, "존재하지 않는 제출입니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "중복되는 이메일입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,"유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED,"만료된 토큰입니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    FILE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 중 오류가 발생했습니다. 다시 시도해 주세요.");

    private final HttpStatus state;
    private final String message;

}