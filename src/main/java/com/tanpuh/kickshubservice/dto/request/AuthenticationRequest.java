package com.tanpuh.kickshubservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = "USERNAME_BLANK")
    private String username;

    @NotNull(message = "PASSWORD_NULL")
    private String password;
}
