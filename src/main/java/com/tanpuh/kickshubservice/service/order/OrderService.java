package com.tanpuh.kickshubservice.service.order;

import com.tanpuh.kickshubservice.dto.request.OrderRequest;
import com.tanpuh.kickshubservice.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
}
