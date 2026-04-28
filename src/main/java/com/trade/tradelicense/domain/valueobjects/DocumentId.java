package com.trade.tradelicense.domain.valueobjects;

import java.util.UUID;

public record DocumentId(UUID value) {

    public DocumentId {
        if (value == null) {
            throw new IllegalArgumentException("DocumentId value must not be null");
        }
    }

    public static DocumentId newId() {
        return new DocumentId(UUID.randomUUID());
    }
}
