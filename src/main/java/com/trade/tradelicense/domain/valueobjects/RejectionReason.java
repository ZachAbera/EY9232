package com.trade.tradelicense.domain.valueobjects;

public record RejectionReason(String value) {

    public RejectionReason {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("RejectionReason value must not be blank");
        }
    }
}
