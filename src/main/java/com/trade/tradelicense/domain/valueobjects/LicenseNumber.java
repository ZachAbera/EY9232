package com.trade.tradelicense.domain.valueobjects;

public record LicenseNumber(String value) {

    public LicenseNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("LicenseNumber value must not be blank");
        }
    }
}
