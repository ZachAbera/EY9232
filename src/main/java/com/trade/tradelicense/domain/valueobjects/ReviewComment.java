package com.trade.tradelicense.domain.valueobjects;

public record ReviewComment(String value) {

    public ReviewComment {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ReviewComment value must not be blank");
        }
    }
}
