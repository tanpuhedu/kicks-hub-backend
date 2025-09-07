package com.tanpuh.kickshubservice.dto.response;

import com.tanpuh.kickshubservice.entity.Cart;
import com.tanpuh.kickshubservice.entity.ProductDetail;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    Integer id;
    Integer quantity;
    ProductDetail productDetail;
    Cart cart;
}
