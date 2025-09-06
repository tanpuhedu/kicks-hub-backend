package com.tanpuh.kickshubservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {
    Integer id;
    String code;
    String name;
    Long price;
    Integer stockQuantity;
    Integer soldQuantity;
    Integer status;

    ProductResponse product;
    ColorResponse color;
    SizeResponse size;
}
