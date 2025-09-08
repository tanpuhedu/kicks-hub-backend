package com.tanpuh.kickshubservice.service.cart;

import com.tanpuh.kickshubservice.dto.request.CartItemRequest;
import com.tanpuh.kickshubservice.dto.response.CartItemResponse;
import com.tanpuh.kickshubservice.dto.response.CartResponse;

public interface CartItemService {
    CartResponse getCartByUsername();
    CartItemResponse addToCart(CartItemRequest request);
    CartItemResponse updateQuantity(Integer id, Integer quantity);
    void delete(Integer id);
}
