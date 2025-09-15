package com.tanpuh.kickshubservice.controller;

import com.tanpuh.kickshubservice.dto.request.OrderRequest;
import com.tanpuh.kickshubservice.dto.response.ApiResponse;
import com.tanpuh.kickshubservice.dto.response.OrderResponse;
import com.tanpuh.kickshubservice.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Order Controller")
public class OrderController {
    OrderService orderService;

    @PostMapping("/storefront/orders")
    @Operation(summary = "create new order")
    public ApiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .message("Create order successfully")
                .data(orderService.createOrder(request))
                .build();
    }
}
