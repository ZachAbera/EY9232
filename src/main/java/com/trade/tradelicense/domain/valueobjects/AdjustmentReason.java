package com.trade.tradelicense.domain.valueobjects;

public record AdjustmentReason(String value) {

    public AdjustmentReason {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("AdjustmentReason value must not be blank");
        }
    }
}
