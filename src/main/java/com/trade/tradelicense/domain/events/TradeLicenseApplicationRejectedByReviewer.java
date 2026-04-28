package com.trade.tradelicense.domain.events;

import com.trade.tradelicense.domain.valueobjects.ApplicationId;
import com.trade.tradelicense.domain.valueobjects.RejectionReason;
import com.trade.tradelicense.domain.valueobjects.ReviewerId;

import java.time.LocalDateTime;

public record TradeLicenseApplicationRejectedByReviewer(
        ApplicationId applicationId,
        ReviewerId reviewerId,
        RejectionReason reason,
        LocalDateTime occurredAt) implements DomainEvent {
}
