package com.tanpuh.kickshubservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // GLOBAL ERROR
    UNCATEGORIZED(9999, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    KEY_INVALID(1001, "Invalid message key", HttpStatus.BAD_REQUEST),

    // CATEGORY ERROR
    CATEGORY_EXISTED(2001, "Category already existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(2002, "Category not existed", HttpStatus.NOT_FOUND),
    CATEGORY_NAME_BLANK(2003, "Category name is required", HttpStatus.BAD_REQUEST),

    //    COLOR ERROR
    COLOR_EXISTED(3001, "Color already existed", HttpStatus.BAD_REQUEST),
    COLOR_NOT_FOUND(3002, "Color not existed", HttpStatus.NOT_FOUND),
    COLOR_NAME_BLANK(3003, "Color name is required", HttpStatus.BAD_REQUEST),
    ;

    private final Integer code;
    private final String message;
    private final HttpStatusCode statusCode;
}
