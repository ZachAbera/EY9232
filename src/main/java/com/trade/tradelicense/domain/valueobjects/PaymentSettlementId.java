package com.trade.tradelicense.domain.valueobjects;

import java.util.UUID;

public record PaymentSettlementId(UUID value) {

    public PaymentSettlementId {
        if (value == null) {
            throw new IllegalArgumentException("PaymentSettlementId value must not be null");
        }
    }

    public static PaymentSettlementId newId() {
        return new PaymentSettlementId(UUID.randomUUID());
    }
}
