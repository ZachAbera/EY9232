package com.trade.tradelicense.domain.factories;

import com.trade.tradelicense.domain.entities.DocumentPackage;
import com.trade.tradelicense.domain.valueobjects.TradeLicenseType;

public class RequiredDocumentPackageFactory {

    public DocumentPackage createFor(TradeLicenseType licenseType) {
        if (licenseType == null) throw new IllegalArgumentException("licenseType must not be null");
        return new DocumentPackage();
    }
}
