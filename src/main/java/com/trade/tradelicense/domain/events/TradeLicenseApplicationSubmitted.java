package com.trade.tradelicense.domain.events;

import com.trade.tradelicense.domain.valueobjects.ApplicantSnapshot;
import com.trade.tradelicense.domain.valueobjects.ApplicationId;

import java.time.LocalDateTime;

public record TradeLicenseApplicationSubmitted(
        ApplicationId applicationId,
        ApplicantSnapshot applicant,
        LocalDateTime occurredAt) implements DomainEvent {
}
