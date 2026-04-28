package com.trade.tradelicense.domain.events;

import com.trade.tradelicense.domain.valueobjects.ApproverId;
import com.trade.tradelicense.domain.valueobjects.ApplicationId;
import com.trade.tradelicense.domain.valueobjects.RejectionReason;

import java.time.LocalDateTime;

public record ReviewedTradeLicenseApplicationRejected(
        ApplicationId applicationId,
        ApproverId approverId,
        RejectionReason reason,
        LocalDateTime occurredAt) implements DomainEvent {
}
