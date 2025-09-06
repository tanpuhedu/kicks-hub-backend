package com.tanpuh.kickshubservice.exception;

import com.tanpuh.kickshubservice.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // xử lí những lỗi chưa xác định được
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleRuntimeException(Exception e) {
        log.error("Exception: ",e);
        return ResponseEntity
                .status(ErrorCode.UNCATEGORIZED.getStatusCode())
                .body(ApiResponse.builder()
                        .status(ErrorCode.UNCATEGORIZED.getCode())
                        .message(ErrorCode.UNCATEGORIZED.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatusCode())
                .body(ApiResponse.builder()
                        .status(e.getErrorCode().getCode())
                        .message(e.getErrorCode().getMessage())
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.KEY_INVALID;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ignored) { }

        ApiResponse apiResponse = ApiResponse.builder()
                .status(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
