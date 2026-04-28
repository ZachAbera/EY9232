package com.trade.tradelicense.domain.events;

import com.trade.tradelicense.domain.valueobjects.AdjustmentReason;
import com.trade.tradelicense.domain.valueobjects.ApplicationId;
import com.trade.tradelicense.domain.valueobjects.ReviewerId;

import java.time.LocalDateTime;

public record TradeLicenseApplicationReturnedForAdjustment(
        ApplicationId applicationId,
        ReviewerId reviewerId,
        AdjustmentReason reason,
        LocalDateTime occurredAt) implements DomainEvent {
}
