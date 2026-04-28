package com.trade.tradelicense.domain.entities;

import com.trade.tradelicense.domain.enums.DocumentStatus;
import com.trade.tradelicense.domain.valueobjects.DocumentId;
import com.trade.tradelicense.domain.valueobjects.DocumentReference;
import com.trade.tradelicense.domain.valueobjects.DocumentType;

import java.time.LocalDateTime;

public class ApplicationDocument {

    private final DocumentId documentId;
    private final DocumentType documentType;
    private final DocumentReference documentReference;
    private DocumentStatus status;
    private final LocalDateTime uploadedAt;

    public ApplicationDocument(DocumentId documentId, DocumentType documentType,
                                DocumentReference documentReference, DocumentStatus status,
                                LocalDateTime uploadedAt) {
        if (documentId == null) throw new IllegalArgumentException("documentId must not be null");
        if (documentType == null) throw new IllegalArgumentException("documentType must not be null");
        if (documentReference == null) throw new IllegalArgumentException("documentReference must not be null");
        if (status == null) throw new IllegalArgumentException("status must not be null");
        if (uploadedAt == null) throw new IllegalArgumentException("uploadedAt must not be null");
        this.documentId = documentId;
        this.documentType = documentType;
        this.documentReference = documentReference;
        this.status = status;
        this.uploadedAt = uploadedAt;
    }

    public DocumentId getDocumentId() {
        return documentId;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public DocumentReference getDocumentReference() {
        return documentReference;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setStatus(DocumentStatus status) {
        if (status == null) throw new IllegalArgumentException("status must not be null");
        this.status = status;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}
