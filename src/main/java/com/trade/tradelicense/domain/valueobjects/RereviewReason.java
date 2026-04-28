package com.trade.tradelicense.domain.valueobjects;

public record RereviewReason(String value) {

    public RereviewReason {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("RereviewReason value must not be blank");
        }
    }
}
