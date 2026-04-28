package com.trade.tradelicense.domain.aggregates;

import com.trade.tradelicense.domain.valueobjects.ApplicantSnapshot;
import com.trade.tradelicense.domain.valueobjects.ApplicationId;
import com.trade.tradelicense.domain.valueobjects.Commodity;
import com.trade.tradelicense.domain.valueobjects.LicenseId;
import com.trade.tradelicense.domain.valueobjects.LicenseNumber;
import com.trade.tradelicense.domain.valueobjects.LicensePeriod;
import com.trade.tradelicense.domain.valueobjects.TradeLicenseType;

import java.time.LocalDate;

public class TradeLicense {

    private final LicenseId id;
    private final LicenseNumber licenseNumber;
    private final ApplicationId sourceApplicationId;
    private final ApplicantSnapshot licenseHolder;
    private final TradeLicenseType licenseType;
    private final Commodity commodity;
    private final LicensePeriod licensePeriod;
    private final LocalDate issuedDate;

    public TradeLicense(LicenseId id, LicenseNumber licenseNumber, ApplicationId sourceApplicationId,
                        ApplicantSnapshot licenseHolder, TradeLicenseType licenseType, Commodity commodity,
                        LicensePeriod licensePeriod, LocalDate issuedDate) {
        if (id == null) throw new IllegalArgumentException("id must not be null");
        if (licenseNumber == null) throw new IllegalArgumentException("licenseNumber must not be null");
        if (sourceApplicationId == null) throw new IllegalArgumentException("sourceApplicationId must not be null");
        if (licenseHolder == null) throw new IllegalArgumentException("licenseHolder must not be null");
        if (licenseType == null) throw new IllegalArgumentException("licenseType must not be null");
        if (commodity == null) throw new IllegalArgumentException("commodity must not be null");
        if (licensePeriod == null) throw new IllegalArgumentException("licensePeriod must not be null");
        if (issuedDate == null) throw new IllegalArgumentException("issuedDate must not be null");
        this.id = id;
        this.licenseNumber = licenseNumber;
        this.sourceApplicationId = sourceApplicationId;
        this.licenseHolder = licenseHolder;
        this.licenseType = licenseType;
        this.commodity = commodity;
        this.licensePeriod = licensePeriod;
        this.issuedDate = issuedDate;
    }

    public LicenseId getId() {
        return id;
    }

    public LicenseNumber getLicenseNumber() {
        return licenseNumber;
    }

    public ApplicationId getSourceApplicationId() {
        return sourceApplicationId;
    }

    public ApplicantSnapshot getLicenseHolder() {
        return licenseHolder;
    }

    public TradeLicenseType getLicenseType() {
        return licenseType;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public LicensePeriod getLicensePeriod() {
        return licensePeriod;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }
}
