package com.tanpuh.kickshubservice.dto.request;

import com.tanpuh.kickshubservice.utils.validators.PhoneConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "USER_USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "USER_PASSWORD_INVALID")
    String password;

    @NotBlank(message = "USER_FULLNAME_BLANK")
    String fullName;

    @NotNull (message = "USER_PHONE_NULL")
    @PhoneConstraint (message = "USER_PHONE_INVALID")
    String phone;

    @NotNull (message = "USER_MAIL_NULL")
    @Email(message = "USER_MAIL_INVALID")
    String email;
}