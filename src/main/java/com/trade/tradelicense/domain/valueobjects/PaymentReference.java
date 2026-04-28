package com.trade.tradelicense.domain.valueobjects;

public record PaymentReference(String value) {

    public PaymentReference {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("PaymentReference value must not be blank");
        }
    }
}
