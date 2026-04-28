package com.trade.tradelicense.domain.valueobjects;

import java.util.UUID;

public record ApplicationId(UUID value) {

    public ApplicationId {
        if (value == null) {
            throw new IllegalArgumentException("ApplicationId value must not be null");
        }
    }

    public static ApplicationId newId() {
        return new ApplicationId(UUID.randomUUID());
    }
}
