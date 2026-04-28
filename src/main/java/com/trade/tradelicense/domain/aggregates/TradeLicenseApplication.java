package com.trade.tradelicense.domain.aggregates;

import com.trade.tradelicense.domain.entities.ApprovalRecord;
import com.trade.tradelicense.domain.entities.ApplicationDocument;
import com.trade.tradelicense.domain.entities.DocumentPackage;
import com.trade.tradelicense.domain.entities.PaymentSettlement;
import com.trade.tradelicense.domain.entities.ReviewRecord;
import com.trade.tradelicense.domain.enums.ApprovalDecision;
import com.trade.tradelicense.domain.enums.ApplicationStatus;
import com.trade.tradelicense.domain.enums.DocumentStatus;
import com.trade.tradelicense.domain.enums.ReviewDecision;
import com.trade.tradelicense.domain.events.DomainEvent;
import com.trade.tradelicense.domain.events.PaymentSettled;
import com.trade.tradelicense.domain.events.ReviewedTradeLicenseApplicationApproved;
import com.trade.tradelicense.domain.events.ReviewedTradeLicenseApplicationRejected;
import com.trade.tradelicense.domain.events.TradeLicenseApplicationAcceptedByReviewer;
import com.trade.tradelicense.domain.events.TradeLicenseApplicationCancelled;
import com.trade.tradelicense.domain.events.TradeLicenseApplicationRejectedByReviewer;
import com.trade.tradelicense.domain.events.TradeLicenseApplicationReturnedForAdjustment;
import com.trade.tradelicense.domain.events.TradeLicenseApplicationSentForRereview;
import com.trade.tradelicense.domain.events.TradeLicenseApplicationSubmitted;
import com.trade.tradelicense.domain.exceptions.InvalidApplicationStateException;
import com.trade.tradelicense.domain.exceptions.MissingRequiredDocumentException;
import com.trade.tradelicense.domain.exceptions.PaymentNotSettledException;
import com.trade.tradelicense.domain.exceptions.UnauthorizedDomainActionException;
import com.trade.tradelicense.domain.valueobjects.Actor;
import com.trade.tradelicense.domain.valueobjects.AdjustmentReason;
import com.trade.tradelicense.domain.valueobjects.ApplicantSnapshot;
import com.trade.tradelicense.domain.valueobjects.ApplicationId;
import com.trade.tradelicense.domain.valueobjects.ApprovalComment;
import com.trade.tradelicense.domain.valueobjects.ApproverId;
import com.trade.tradelicense.domain.valueobjects.Commodity;
import com.trade.tradelicense.domain.valueobjects.DocumentId;
import com.trade.tradelicense.domain.valueobjects.DocumentReference;
import com.trade.tradelicense.domain.valueobjects.DocumentType;
import com.trade.tradelicense.domain.valueobjects.Money;
import com.trade.tradelicense.domain.valueobjects.PaymentReference;
import com.trade.tradelicense.domain.valueobjects.PaymentSettlementId;
import com.trade.tradelicense.domain.valueobjects.RejectionReason;
import com.trade.tradelicense.domain.valueobjects.RereviewReason;
import com.trade.tradelicense.domain.valueobjects.ReviewComment;
import com.trade.tradelicense.domain.valueobjects.ReviewerId;
import com.trade.tradelicense.domain.valueobjects.TradeLicenseType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TradeLicenseApplication {

    private ApplicationId id;
    private ApplicantSnapshot applicant;
    private TradeLicenseType licenseType;
    private Commodity commodity;
    private DocumentPackage documentPackage;
    private PaymentSettlement paymentSettlement;
    private ReviewRecord reviewRecord;
    private ApprovalRecord approvalRecord;
    private ApplicationStatus status;
    private final List<DomainEvent> domainEvents;

    private TradeLicenseApplication() {
        this.domainEvents = new ArrayList<>();
    }

    public static TradeLicenseApplication createDraft(ApplicationId id, ApplicantSnapshot applicant,
                                                       TradeLicenseType licenseType, Commodity commodity) {
        if (id == null) throw new IllegalArgumentException("id must not be null");
        if (applicant == null) throw new IllegalArgumentException("applicant must not be null");
        if (licenseType == null) throw new IllegalArgumentException("licenseType must not be null");
        if (commodity == null) throw new IllegalArgumentException("commodity must not be null");

        TradeLicenseApplication application = new TradeLicenseApplication();
        application.id = id;
        application.applicant = applicant;
        application.licenseType = licenseType;
        application.commodity = commodity;
        application.documentPackage = new DocumentPackage();
        application.status = ApplicationStatus.DRAFT;
        return application;
    }

    public void attachDocument(DocumentId documentId, DocumentType documentType, DocumentReference documentReference) {
        if (documentId == null) throw new IllegalArgumentException("documentId must not be null");
        if (documentType == null) throw new IllegalArgumentException("documentType must not be null");
        if (documentReference == null) throw new IllegalArgumentException("documentReference must not be null");
        ApplicationDocument document = new ApplicationDocument(documentId, documentType, documentReference,
                DocumentStatus.UPLOADED, LocalDateTime.now());
        documentPackage.addDocument(document);
    }

    public void settlePayment(PaymentSettlementId settlementId, Money amount, PaymentReference reference) {
        if (settlementId == null) throw new IllegalArgumentException("settlementId must not be null");
        if (amount == null) throw new IllegalArgumentException("amount must not be null");
        if (reference == null) throw new IllegalArgumentException("reference must not be null");
        if (this.paymentSettlement == null) {
            this.paymentSettlement = new PaymentSettlement(settlementId, amount, reference);
        }
        this.paymentSettlement.settle();
        domainEvents.add(new PaymentSettled(id, LocalDateTime.now()));
    }

    public void submit(Actor actor) {
        if (!actor.isCustomerOrLicensee()) {
            throw new UnauthorizedDomainActionException("Only CUSTOMER or LICENSEE can submit");
        }
        if (status != ApplicationStatus.DRAFT) {
            throw new InvalidApplicationStateException("Submit is only allowed from DRAFT status");
        }
        if (!documentPackage.hasDocuments()) {
            throw new MissingRequiredDocumentException("At least one document must be attached before submission");
        }
        if (paymentSettlement == null || !paymentSettlement.isSettled()) {
            throw new PaymentNotSettledException("Payment must be settled before submission");
        }
        this.status = ApplicationStatus.PENDING_REVIEW;
        domainEvents.add(new TradeLicenseApplicationSubmitted(id, applicant, LocalDateTime.now()));
    }

    public void cancel(Actor actor) {
        if (!actor.isCustomerOrLicensee()) {
            throw new UnauthorizedDomainActionException("Only CUSTOMER or LICENSEE can cancel");
        }
        if (status != ApplicationStatus.DRAFT) {
            throw new InvalidApplicationStateException("Cancel is only allowed from DRAFT status");
        }
        this.status = ApplicationStatus.CANCELLED;
        domainEvents.add(new TradeLicenseApplicationCancelled(id, LocalDateTime.now()));
    }

    public void review(Actor actor, ReviewDecision decision, String comment) {
        if (!actor.isReviewer()) {
            throw new UnauthorizedDomainActionException("Only REVIEWER can review");
        }
        if (status != ApplicationStatus.PENDING_REVIEW) {
            throw new InvalidApplicationStateException("Review is only allowed from PENDING_REVIEW status");
        }
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("Review comment must not be blank");
        }
        ReviewerId reviewerId = new ReviewerId(actor.userId().value());
        this.reviewRecord = new ReviewRecord(reviewerId, decision, new ReviewComment(comment), LocalDateTime.now());

        switch (decision) {
            case ACCEPT -> {
                this.status = ApplicationStatus.PENDING_APPROVAL;
                domainEvents.add(new TradeLicenseApplicationAcceptedByReviewer(id, reviewerId, LocalDateTime.now()));
            }
            case REJECT -> {
                this.status = ApplicationStatus.REJECTED;
                domainEvents.add(new TradeLicenseApplicationRejectedByReviewer(id, reviewerId,
                        new RejectionReason(comment), LocalDateTime.now()));
            }
            case ADJUST -> {
                this.status = ApplicationStatus.RETURNED_FOR_ADJUSTMENT;
                domainEvents.add(new TradeLicenseApplicationReturnedForAdjustment(id, reviewerId,
                        new AdjustmentReason(comment), LocalDateTime.now()));
            }
        }
    }

    public void resubmitAfterAdjustment(Actor actor) {
        if (!actor.isCustomerOrLicensee()) {
            throw new UnauthorizedDomainActionException("Only CUSTOMER or LICENSEE can resubmit");
        }
        if (status != ApplicationStatus.RETURNED_FOR_ADJUSTMENT) {
            throw new InvalidApplicationStateException("Resubmit is only allowed from RETURNED_FOR_ADJUSTMENT status");
        }
        this.status = ApplicationStatus.PENDING_REVIEW;
        domainEvents.add(new TradeLicenseApplicationSubmitted(id, applicant, LocalDateTime.now()));
    }

    public void approve(Actor actor, ApprovalDecision decision, String comment) {
        if (!actor.isApprover()) {
            throw new UnauthorizedDomainActionException("Only APPROVER can approve");
        }
        if (status != ApplicationStatus.PENDING_APPROVAL) {
            throw new InvalidApplicationStateException("Approval is only allowed from PENDING_APPROVAL status");
        }
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("Approval comment must not be blank");
        }
        ApproverId approverId = new ApproverId(actor.userId().value());
        this.approvalRecord = new ApprovalRecord(approverId, decision, new ApprovalComment(comment), LocalDateTime.now());

        switch (decision) {
            case APPROVE -> {
                this.status = ApplicationStatus.APPROVED;
                domainEvents.add(new ReviewedTradeLicenseApplicationApproved(id, approverId, LocalDateTime.now()));
            }
            case REJECT -> {
                this.status = ApplicationStatus.REJECTED;
                domainEvents.add(new ReviewedTradeLicenseApplicationRejected(id, approverId,
                        new RejectionReason(comment), LocalDateTime.now()));
            }
            case REREVIEW -> {
                this.status = ApplicationStatus.PENDING_REVIEW;
                domainEvents.add(new TradeLicenseApplicationSentForRereview(id, approverId,
                        new RereviewReason(comment), LocalDateTime.now()));
            }
        }
    }

    public void markLicenseIssued() {
        if (status != ApplicationStatus.APPROVED) {
            throw new InvalidApplicationStateException("License can only be issued from APPROVED status");
        }
        this.status = ApplicationStatus.LICENSE_ISSUED;
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = Collections.unmodifiableList(new ArrayList<>(domainEvents));
        domainEvents.clear();
        return events;
    }

    public ApplicationId getId() {
        return id;
    }

    public ApplicantSnapshot getApplicant() {
        return applicant;
    }

    public TradeLicenseType getLicenseType() {
        return licenseType;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public DocumentPackage getDocumentPackage() {
        return documentPackage;
    }

    public PaymentSettlement getPaymentSettlement() {
        return paymentSettlement;
    }

    public ReviewRecord getReviewRecord() {
        return reviewRecord;
    }

    public ApprovalRecord getApprovalRecord() {
        return approvalRecord;
    }

    public ApplicationStatus getStatus() {
        return status;
    }
}
