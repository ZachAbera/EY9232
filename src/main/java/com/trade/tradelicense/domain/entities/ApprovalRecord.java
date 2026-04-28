package com.trade.tradelicense.domain.entities;

import com.trade.tradelicense.domain.enums.ApprovalDecision;
import com.trade.tradelicense.domain.valueobjects.ApprovalComment;
import com.trade.tradelicense.domain.valueobjects.ApproverId;

import java.time.LocalDateTime;

public class ApprovalRecord {

    private final ApproverId approver;
    private final ApprovalDecision decision;
    private final ApprovalComment comment;
    private final LocalDateTime approvedAt;

    public ApprovalRecord(ApproverId approver, ApprovalDecision decision, ApprovalComment comment, LocalDateTime approvedAt) {
        if (approver == null) throw new IllegalArgumentException("approver must not be null");
        if (decision == null) throw new IllegalArgumentException("decision must not be null");
        if (comment == null) throw new IllegalArgumentException("comment must not be null");
        if (approvedAt == null) throw new IllegalArgumentException("approvedAt must not be null");
        this.approver = approver;
        this.decision = decision;
        this.comment = comment;
        this.approvedAt = approvedAt;
    }

    public ApproverId getApprover() {
        return approver;
    }

    public ApprovalDecision getDecision() {
        return decision;
    }

    public ApprovalComment getComment() {
        return comment;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }
}
