package com.trade.tradelicense.domain.valueobjects;

import java.util.UUID;

public record ApproverId(UUID value) {

    public ApproverId {
        if (value == null) {
            throw new IllegalArgumentException("ApproverId value must not be null");
        }
    }

    public static ApproverId newId() {
        return new ApproverId(UUID.randomUUID());
    }
}
