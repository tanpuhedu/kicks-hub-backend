package com.tanpuh.kickshubservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class ColorRequest {
    @NotBlank (message = "COLOR_NAME_BLANK")
    private String name;
}
