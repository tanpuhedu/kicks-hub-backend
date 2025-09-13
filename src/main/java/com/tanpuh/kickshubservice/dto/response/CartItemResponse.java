package com.tanpuh.kickshubservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    Integer id;
    Integer quantity;
    ProductDetailResponse productDetail;
    Integer cartId;
}
