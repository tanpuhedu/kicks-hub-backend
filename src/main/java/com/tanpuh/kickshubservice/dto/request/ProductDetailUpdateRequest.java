package com.tanpuh.kickshubservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailUpdateRequest {
    @NotBlank(message = "PRODUCT_DETAIL_NAME_BLANK")
    String name;

    @NotNull(message = "PRODUCT_DETAIL_PRICE_NULL")
    @Positive(message = "PRODUCT_DETAIL_PRICE_NEGATIVE_OR_ZERO")
    Long price;

    @NotNull(message = "PRODUCT_DETAIL_STOCK_QUANTITY_NULL")
    @PositiveOrZero(message = "PRODUCT_DETAIL_STOCK_QUANTITY_NEGATIVE")
    Integer stockQuantity;

    @NotNull(message = "PRODUCT_DETAIL_STATUS_NULL")
    @Range(min = 0, max = 1, message = "PRODUCT_DETAIL_STATUS_INVALID")
    Integer status;

    @NotNull(message = "PRODUCT_DETAIL_PRODUCT_NULL")
    Integer productId;

    @NotNull(message = "PRODUCT_DETAIL_COLOR_NULL")
    Integer colorId;

    @NotNull(message = "PRODUCT_DETAIL_SIZE_NULL")
    Integer sizeId;
}
