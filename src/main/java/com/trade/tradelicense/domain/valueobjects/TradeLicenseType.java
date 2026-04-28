package com.trade.tradelicense.domain.valueobjects;

public record TradeLicenseType(String code, String name) {

    public TradeLicenseType {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("TradeLicenseType code must not be blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("TradeLicenseType name must not be blank");
        }
    }
}
