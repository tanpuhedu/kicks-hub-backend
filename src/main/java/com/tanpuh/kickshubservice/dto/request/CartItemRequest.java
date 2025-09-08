package com.tanpuh.kickshubservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemRequest {
    @NotNull(message = "CART_ITEM_QUANTITY_NULL")
    @PositiveOrZero(message = "CART_ITEM_QUANTITY_NEGATIVE")
    Integer quantity;

    Integer productDetailId;
    Integer cartId;
}
