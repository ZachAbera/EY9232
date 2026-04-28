package com.trade.tradelicense.domain.valueobjects;

public record DocumentReference(String url) {

    public DocumentReference {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("DocumentReference url must not be blank");
        }
    }
}
