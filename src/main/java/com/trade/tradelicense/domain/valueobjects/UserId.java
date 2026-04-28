package com.trade.tradelicense.domain.valueobjects;

import java.util.UUID;

public record UserId(UUID value) {

    public UserId {
        if (value == null) {
            throw new IllegalArgumentException("UserId value must not be null");
        }
    }

    public static UserId newId() {
        return new UserId(UUID.randomUUID());
    }
}
