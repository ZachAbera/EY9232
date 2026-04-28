package com.trade.tradelicense.domain.factories;

import com.trade.tradelicense.domain.aggregates.TradeLicense;
import com.trade.tradelicense.domain.aggregates.TradeLicenseApplication;
import com.trade.tradelicense.domain.enums.ApplicationStatus;
import com.trade.tradelicense.domain.exceptions.InvalidApplicationStateException;
import com.trade.tradelicense.domain.valueobjects.LicenseId;
import com.trade.tradelicense.domain.valueobjects.LicenseNumber;
import com.trade.tradelicense.domain.valueobjects.LicensePeriod;

import java.time.LocalDate;
import java.util.UUID;

public class TradeLicenseFactory {

    public TradeLicense issue(TradeLicenseApplication application, LicensePeriod licensePeriod) {
        if (application == null) throw new IllegalArgumentException("application must not be null");
        if (licensePeriod == null) throw new IllegalArgumentException("licensePeriod must not be null");
        if (application.getStatus() != ApplicationStatus.APPROVED) {
            throw new InvalidApplicationStateException(
                    "Trade license can only be issued from an APPROVED application");
        }
        TradeLicense license = new TradeLicense(
                LicenseId.newId(),
                new LicenseNumber("TL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()),
                application.getId(),
                application.getApplicant(),
                application.getLicenseType(),
                application.getCommodity(),
                licensePeriod,
                LocalDate.now()
        );
        application.markLicenseIssued();
        return license;
    }
}
