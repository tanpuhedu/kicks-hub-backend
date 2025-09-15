package com.tanpuh.kickshubservice.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    PAID("PAID"),
    SHIPPING("SHIPPING"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

    private final String name;
}
