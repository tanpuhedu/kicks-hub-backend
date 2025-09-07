package com.tanpuh.kickshubservice.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PredefinedRole {
    ADMIN(1, "ADMIN"),
    USER(2, "USER");

    private final int id;
    private final String name;
}
