package com.example.server.common.exception;

import com.example.server.dto.responseDTO.ErrorResponse;
import io.jsonwebtoken.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CustomException.class, JwtException.class})
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode().getState().value(),
            e.getMessage());
        return ResponseEntity.status(e.getErrorCode().getState()).body(errorResponse);
    }


}
