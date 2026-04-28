package com.trade.tradelicense.domain.entities;

import com.trade.tradelicense.domain.enums.ReviewDecision;
import com.trade.tradelicense.domain.valueobjects.ReviewComment;
import com.trade.tradelicense.domain.valueobjects.ReviewerId;

import java.time.LocalDateTime;

public class ReviewRecord {

    private final ReviewerId reviewer;
    private final ReviewDecision decision;
    private final ReviewComment comment;
    private final LocalDateTime reviewedAt;

    public ReviewRecord(ReviewerId reviewer, ReviewDecision decision, ReviewComment comment, LocalDateTime reviewedAt) {
        if (reviewer == null) throw new IllegalArgumentException("reviewer must not be null");
        if (decision == null) throw new IllegalArgumentException("decision must not be null");
        if (comment == null) throw new IllegalArgumentException("comment must not be null");
        if (reviewedAt == null) throw new IllegalArgumentException("reviewedAt must not be null");
        this.reviewer = reviewer;
        this.decision = decision;
        this.comment = comment;
        this.reviewedAt = reviewedAt;
    }

    public ReviewerId getReviewer() {
        return reviewer;
    }

    public ReviewDecision getDecision() {
        return decision;
    }

    public ReviewComment getComment() {
        return comment;
    }

    public LocalDateTime getReviewedAt() {
        return reviewedAt;
    }
}
