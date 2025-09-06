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

    //  COLOR ERROR
    COLOR_EXISTED(3001, "Color already existed", HttpStatus.BAD_REQUEST),
    COLOR_NOT_FOUND(3002, "Color not existed", HttpStatus.NOT_FOUND),
    COLOR_NAME_BLANK(3003, "Color name is required", HttpStatus.BAD_REQUEST),

    // SIZE ERROR
    SIZE_EXISTED(4001, "Size already existed", HttpStatus.BAD_REQUEST),
    SIZE_NOT_FOUND(4002, "Size not existed", HttpStatus.NOT_FOUND),
    SIZE_NAME_BLANK(4003, "Size name is required", HttpStatus.BAD_REQUEST),

    // PRODUCT ERROR
    PRODUCT_EXISTED(5001, "Product already existed", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(5002, "Product not existed", HttpStatus.NOT_FOUND),
    PRODUCT_CODE_BLANK(5003, "Product code is required",HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_BLANK(5004, "Product name is required",HttpStatus.BAD_REQUEST),
    PRODUCT_STATUS_NULL(5005, "Product status is required",HttpStatus.BAD_REQUEST),
    PRODUCT_STATUS_INVALID
            (5006, "Product status must be {min} (INACTIVE) or {max} (ACTIVE)",HttpStatus.BAD_REQUEST),
    PRODUCT_CATEGORY_NULL(5007, "Product's category is required",HttpStatus.BAD_REQUEST),

    // PRODUCT DETAIL ERROR
    PRODUCT_DETAIL_EXISTED(6001, "Product detail already existed", HttpStatus.BAD_REQUEST),
    PRODUCT_DETAIL_NOT_FOUND(6002, "Product detail not existed", HttpStatus.NOT_FOUND),
    PRODUCT_DETAIL_CODE_BLANK(6003, "Product detail code is required",HttpStatus.BAD_REQUEST),
    PRODUCT_DETAIL_NAME_BLANK(6004, "Product detail name is required",HttpStatus.BAD_REQUEST),
    PRODUCT_DETAIL_PRICE_NULL(6005, "Product detail price is required",HttpStatus.BAD_REQUEST),
    PRODUCT_DETAIL_PRICE_NEGATIVE_OR_ZERO
            (6006, "Product detail price must be greater than 0",HttpStatus.BAD_REQUEST),
    PRODUCT_DETAIL_STOCK_QUANTITY_NULL
            (6007, "Product detail stock quantity is required",HttpStatus.BAD_REQUEST),
    PRODUCT_DETAIL_STOCK_QUANTITY_NEGATIVE(6008,
            "Product detail stock quantity must be greater than or equal to 0",
            HttpStatus.BAD_REQUEST),
    PRODUCT_DETAIL_STATUS_NULL(6009, "Product detail status is required",HttpStatus.BAD_REQUEST),
    PRODUCT_DETAIL_STATUS_INVALID(6010,
            "Product detail status must be {min} (INACTIVE) or {max} (ACTIVE)",
            HttpStatus.BAD_REQUEST),
    PRODUCT_DETAIL_PRODUCT_NULL(6011, "Product detail's product is required",HttpStatus.BAD_REQUEST),
    PRODUCT_DETAIL_SIZE_NULL(6012, "Product detail's size is required",HttpStatus.BAD_REQUEST),
    PRODUCT_DETAIL_COLOR_NULL(6013, "Product detail's color is required",HttpStatus.BAD_REQUEST),
    ;

    private final Integer code;
    private final String message;
    private final HttpStatusCode statusCode;
}
