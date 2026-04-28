package com.trade.tradelicense.domain.valueobjects;

import java.util.UUID;

public record ReviewerId(UUID value) {

    public ReviewerId {
        if (value == null) {
            throw new IllegalArgumentException("ReviewerId value must not be null");
        }
    }

    public static ReviewerId newId() {
        return new ReviewerId(UUID.randomUUID());
    }
}
