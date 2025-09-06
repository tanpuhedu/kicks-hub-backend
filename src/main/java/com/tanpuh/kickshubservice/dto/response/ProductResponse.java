package com.tanpuh.kickshubservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    Integer id;
    String code;
    String name;
    Long price;
    Integer stockQuantity;
    Integer soldQuantity;
    String description;
    Integer status;

    CategoryResponse category;
}
