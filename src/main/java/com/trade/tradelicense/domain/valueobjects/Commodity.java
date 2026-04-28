package com.trade.tradelicense.domain.valueobjects;

public record Commodity(String code, String name) {

    public Commodity {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Commodity code must not be blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Commodity name must not be blank");
        }
    }
}
