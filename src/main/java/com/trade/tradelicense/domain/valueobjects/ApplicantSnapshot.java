package com.trade.tradelicense.domain.valueobjects;

public record ApplicantSnapshot(UserId userId, String fullName, String email) {

    public ApplicantSnapshot {
        if (userId == null) {
            throw new IllegalArgumentException("ApplicantSnapshot userId must not be null");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("ApplicantSnapshot fullName must not be blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("ApplicantSnapshot email must not be blank");
        }
    }
}
