package com.tanpuh.kickshubservice.dto.response;

import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class OrderItemResponse {
    Integer id;
    Integer productDetailId;
    String productDetailName;
    Long price;
    Integer quantity;
    Long subtotal;
    Integer orderId;
}
