package com.tanpuh.kickshubservice.controller;

import com.tanpuh.kickshubservice.dto.request.CartItemRequest;
import com.tanpuh.kickshubservice.dto.response.ApiResponse;
import com.tanpuh.kickshubservice.dto.response.CartItemResponse;
import com.tanpuh.kickshubservice.dto.response.CartResponse;
import com.tanpuh.kickshubservice.service.cart.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Cart Item Controller")
public class CartController {
    CartItemService cartItemService;

    @GetMapping
    @Operation(summary = "view cart by username from token")
    public ApiResponse<CartResponse> viewCart() {
        return ApiResponse.<CartResponse>builder()
                .data(cartItemService.getCartByUsername())
                .message("Get cart successfully")
                .build();
    }

    @PostMapping
    @Operation(summary = "add to cart")
    public ApiResponse<CartItemResponse> addCartItem(@RequestBody @Valid CartItemRequest request) {
        return ApiResponse.<CartItemResponse>builder()
                .data(cartItemService.addToCart(request))
                .message("Create cart item successfully")
                .build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update cart item quantity by id")
    public ApiResponse<CartItemResponse> updateQuantity(@PathVariable Integer id, @RequestParam Integer quantity) {
        return ApiResponse.<CartItemResponse>builder()
                .data(cartItemService.updateQuantity(id, quantity))
                .message("Update cart item successfully")
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete cart item by id")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        cartItemService.delete(id);
        return ApiResponse.<Void>builder()
                .message("Delete cart item successfully")
                .build();
    }
}
