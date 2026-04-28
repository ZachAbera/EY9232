package com.trade.tradelicense.domain.events;

import com.trade.tradelicense.domain.valueobjects.ApproverId;
import com.trade.tradelicense.domain.valueobjects.ApplicationId;
import com.trade.tradelicense.domain.valueobjects.RereviewReason;

import java.time.LocalDateTime;

public record TradeLicenseApplicationSentForRereview(
        ApplicationId applicationId,
        ApproverId approverId,
        RereviewReason reason,
        LocalDateTime occurredAt) implements DomainEvent {
}
