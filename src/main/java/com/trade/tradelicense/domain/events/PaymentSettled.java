package com.trade.tradelicense.domain.events;

import com.trade.tradelicense.domain.valueobjects.ApplicationId;

import java.time.LocalDateTime;

public record PaymentSettled(
        ApplicationId applicationId,
        LocalDateTime occurredAt) implements DomainEvent {
}
