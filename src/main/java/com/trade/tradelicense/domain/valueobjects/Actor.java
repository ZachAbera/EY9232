package com.trade.tradelicense.domain.valueobjects;

import com.trade.tradelicense.domain.enums.UserRole;

public record Actor(UserId userId, UserRole role) {

    public Actor {
        if (userId == null) {
            throw new IllegalArgumentException("Actor userId must not be null");
        }
        if (role == null) {
            throw new IllegalArgumentException("Actor role must not be null");
        }
    }

    public boolean isCustomerOrLicensee() {
        return role == UserRole.CUSTOMER || role == UserRole.LICENSEE;
    }

    public boolean isReviewer() {
        return role == UserRole.REVIEWER;
    }

    public boolean isApprover() {
        return role == UserRole.APPROVER;
    }
}
