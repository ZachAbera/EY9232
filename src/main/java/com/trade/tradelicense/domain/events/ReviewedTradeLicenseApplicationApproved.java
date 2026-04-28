package com.trade.tradelicense.domain.events;

import com.trade.tradelicense.domain.valueobjects.ApproverId;
import com.trade.tradelicense.domain.valueobjects.ApplicationId;

import java.time.LocalDateTime;

public record ReviewedTradeLicenseApplicationApproved(
        ApplicationId applicationId,
        ApproverId approverId,
        LocalDateTime occurredAt) implements DomainEvent {
}
