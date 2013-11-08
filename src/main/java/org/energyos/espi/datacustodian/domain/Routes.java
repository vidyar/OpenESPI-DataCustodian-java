package org.energyos.espi.datacustodian.domain;

public class Routes {
    public static final String DataCustodianHome = "/custodian/home";
    public static final String DataCustodianNotifyThirdParty = "/espi/1_1/NotifyThirdParty";
    public static final String DataCustodianRemoveAllOAuthTokens = "/custodian/removealltokens";
    public static final String DataCustodianAuthorization = "/espi/1_1/resource/Authorization/{AuthorizationID}";

    public static final String CUSTODIAN_RETAILCUSTOMERS_USAGEPOINTS_FORM = "/custodian/retailcustomers/usagepoints/form";
    public static final String CUSTODIAN_RETAILCUSTOMERS = "/custodian/retailcustomers";

    public static final String DataCustodianRESTUsagePointCollection = "/espi/1_1/resource/RetailCustomer/{retailCustomerId}/UsagePoint";
    public static final String DataCustodianRESTUsagePointCreate = "/espi/1_1/resource/RetailCustomer/{retailCustomerId}/UsagePoint";
    public static final String DataCustodianRESTUsagePointMember = "/espi/1_1/resource/RetailCustomer/{retailCustomerHashedId}/UsagePoint/{usagePointHashedId}";
    public static final String DataCustodianRESTUsagePointUpdate = "/espi/1_1/resource/RetailCustomer/{retailCustomerHashedId}/UsagePoint/{usagePointHashedId}";
    public static final String DataCustodianRESTUsagePointDelete = "/espi/1_1/resource/RetailCustomer/{retailCustomerHashedId}/UsagePoint/{usagePointHashedId}";

    public static final String DataCustodianRESTIntervalBlockMember = "/espi/1_1/resource/RetailCustomer/{RetailCustomerID}/UsagePoint/{UsagePointID}/MeterReading/{MeterReadingID}/IntervalBlock/{IntervalBlockID}";

    public static final String DataCustodianSubscription = "/espi/1_1/resource/Subscription/{subscriptionHashedId}";

    public static String newDataCustodianRESTUsagePointCollection(String retailCustomerHashedId) {
        return DataCustodianRESTUsagePointCollection.replace("{retailCustomerId}", retailCustomerHashedId);
    }

    public static String newDataCustodianRESTUsagePointMember(String retailCustomerHashedId, String usagePointHashedId) {
        return DataCustodianRESTUsagePointMember.replace("{retailCustomerHashedId}", retailCustomerHashedId).replace("{usagePointHashedId}", usagePointHashedId);
    }

    public static String newDataCustodianSubscription(String subscriptionHashedId) {
        return DataCustodianSubscription.replace("{subscriptionHashedId}", subscriptionHashedId);
    }

    public static String newDataCustodianRESTIntervalBlockMember(String retailCustomerHashedId, String usagePointHashedId, String meterReadingId, String intervalBlockId) {
        return DataCustodianRESTUsagePointMember
                .replace("{retailCustomerHashedId}", retailCustomerHashedId)
                .replace("{usagePointHashedId}", usagePointHashedId)
                .replace("{meterReadingId}", meterReadingId)
                .replace("{intervalBlockId}", intervalBlockId);
    }
}

