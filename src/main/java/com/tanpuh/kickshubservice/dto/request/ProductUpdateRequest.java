package com.tanpuh.kickshubservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateRequest {
    @NotBlank(message = "PRODUCT_NAME_BLANK")
    String name;

    String description;

    @NotNull(message = "PRODUCT_STATUS_NULL")
    @Range(min = 0, max = 1, message = "PRODUCT_STATUS_INVALID")
    Integer status;

    @NotNull(message = "PRODUCT_CATEGORY_NULL")
    Integer categoryId;
}
