package com.tanpuh.kickshubservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    Integer id;
    Integer totalQuantity;
    UserResponse user;
    List<CartItemResponse> cartItems;
}
