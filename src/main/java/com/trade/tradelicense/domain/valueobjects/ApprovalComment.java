package com.trade.tradelicense.domain.valueobjects;

public record ApprovalComment(String value) {

    public ApprovalComment {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ApprovalComment value must not be blank");
        }
    }
}
