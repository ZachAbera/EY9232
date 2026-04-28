package com.trade.tradelicense.domain.entities;

import com.trade.tradelicense.domain.enums.PaymentStatus;
import com.trade.tradelicense.domain.exceptions.DomainException;
import com.trade.tradelicense.domain.valueobjects.Money;
import com.trade.tradelicense.domain.valueobjects.PaymentReference;
import com.trade.tradelicense.domain.valueobjects.PaymentSettlementId;

import java.time.LocalDateTime;

public class PaymentSettlement {

    private final PaymentSettlementId id;
    private final Money amount;
    private final PaymentReference reference;
    private PaymentStatus status;
    private LocalDateTime settledAt;

    public PaymentSettlement(PaymentSettlementId id, Money amount, PaymentReference reference) {
        if (id == null) throw new IllegalArgumentException("id must not be null");
        if (amount == null) throw new IllegalArgumentException("amount must not be null");
        if (reference == null) throw new IllegalArgumentException("reference must not be null");
        this.id = id;
        this.amount = amount;
        this.reference = reference;
        this.status = PaymentStatus.NOT_PAID;
    }

    public void settle() {
        if (status == PaymentStatus.SETTLED) {
            throw new DomainException("Payment is already settled");
        }
        if (status == PaymentStatus.FAILED) {
            throw new DomainException("Cannot settle a failed payment");
        }
        this.status = PaymentStatus.SETTLED;
        this.settledAt = LocalDateTime.now();
    }

    public boolean isSettled() {
        return status == PaymentStatus.SETTLED;
    }

    public PaymentSettlementId getId() {
        return id;
    }

    public Money getAmount() {
        return amount;
    }

    public PaymentReference getReference() {
        return reference;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getSettledAt() {
        return settledAt;
    }
}
