package com.tanpuh.kickshubservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Integer id;
    Integer userId;
    String phone;
    String fullName;
    String status;
    Long totalAmount;
    String note;
    Long deliveryFee;
    String deliveryAddress;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<OrderItemResponse> orderItems;
}
