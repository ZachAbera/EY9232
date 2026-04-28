package com.trade.tradelicense.domain.valueobjects;

import java.util.UUID;

public record LicenseId(UUID value) {

    public LicenseId {
        if (value == null) {
            throw new IllegalArgumentException("LicenseId value must not be null");
        }
    }

    public static LicenseId newId() {
        return new LicenseId(UUID.randomUUID());
    }
}
