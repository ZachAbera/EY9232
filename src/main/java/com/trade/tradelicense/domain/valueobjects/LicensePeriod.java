package com.trade.tradelicense.domain.valueobjects;

import java.time.LocalDate;

public record LicensePeriod(LocalDate startDate, LocalDate endDate) {

    public LicensePeriod {
        if (startDate == null) {
            throw new IllegalArgumentException("LicensePeriod startDate must not be null");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("LicensePeriod endDate must not be null");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("LicensePeriod endDate must not be before startDate");
        }
    }
}
