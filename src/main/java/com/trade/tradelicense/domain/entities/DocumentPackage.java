package com.trade.tradelicense.domain.entities;

import com.trade.tradelicense.domain.valueobjects.DocumentType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DocumentPackage {

    private final List<ApplicationDocument> documents;

    public DocumentPackage() {
        this.documents = new ArrayList<>();
    }

    public void addDocument(ApplicationDocument document) {
        if (document == null) throw new IllegalArgumentException("document must not be null");
        this.documents.add(document);
    }

    public boolean hasDocuments() {
        return !documents.isEmpty();
    }

    public boolean hasDocumentOfType(DocumentType documentType) {
        if (documentType == null) throw new IllegalArgumentException("documentType must not be null");
        return documents.stream()
                .anyMatch(d -> d.getDocumentType().equals(documentType));
    }

    public List<ApplicationDocument> getDocuments() {
        return Collections.unmodifiableList(documents);
    }
}
