package com.trade.tradelicense.domain.valueobjects;

import java.math.BigDecimal;
import java.util.Currency;

public record Money(BigDecimal amount, String currency) {

    public Money {
        if (amount == null) {
            throw new IllegalArgumentException("Money amount must not be null");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Money currency must not be blank");
        }
        try {
            Currency.getInstance(currency);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Money currency is not a valid ISO 4217 code: " + currency);
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money amount must not be negative");
        }
    }
}
