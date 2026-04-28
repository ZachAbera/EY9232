package com.trade.tradelicense.domain.valueobjects;

public record DocumentType(String code, String name) {

    public DocumentType {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("DocumentType code must not be blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("DocumentType name must not be blank");
        }
    }
}
