package com.trade.tradelicense.domain.events;

import com.trade.tradelicense.domain.valueobjects.ApplicationId;
import com.trade.tradelicense.domain.valueobjects.LicenseId;

import java.time.LocalDateTime;

public record TradeLicenseIssued(
        LicenseId licenseId,
        ApplicationId sourceApplicationId,
        LocalDateTime occurredAt) implements DomainEvent {
}
