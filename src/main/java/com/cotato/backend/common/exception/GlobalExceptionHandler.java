package com.cotato.backend.common.exception;

import com.cotato.backend.common.response.DataResponse;
import com.cotato.backend.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<DataResponse<Void>> handleCustomException(CustomException ex, HttpServletRequest request) {
        ErrorCode code = ex.getErrorCode();
        log.warn("CustomException: {} at {}", code.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(DataResponse.failure(code), code.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataResponse<Void>> handleException(Exception ex, HttpServletRequest request) {
        log.error("Unexpected error: {} at {}", ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(DataResponse.failure(ErrorCode.INTERNAL_SERVER_ERROR), ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus());
    }
}
