package com.tanpuh.kickshubservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    String phone;
    String fullName;
    String deliveryAddress;
    String note;
    Integer cartId;
}
