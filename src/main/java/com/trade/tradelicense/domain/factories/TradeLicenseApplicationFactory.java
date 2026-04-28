package com.trade.tradelicense.domain.factories;

import com.trade.tradelicense.domain.aggregates.TradeLicenseApplication;
import com.trade.tradelicense.domain.valueobjects.ApplicantSnapshot;
import com.trade.tradelicense.domain.valueobjects.ApplicationId;
import com.trade.tradelicense.domain.valueobjects.Commodity;
import com.trade.tradelicense.domain.valueobjects.TradeLicenseType;

public class TradeLicenseApplicationFactory {

    public TradeLicenseApplication createDraft(ApplicantSnapshot applicant, TradeLicenseType licenseType,
                                                Commodity commodity) {
        return TradeLicenseApplication.createDraft(ApplicationId.newId(), applicant, licenseType, commodity);
    }
}
