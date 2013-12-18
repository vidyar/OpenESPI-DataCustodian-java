INSERT INTO application_information (uuid, dataCustodianThirdPartyId, thirdPartyApplicationName, thirdPartyDefaultScopeResource, thirdPartyNotifyUri, thirdPartyDefaultOAuthCallback, dataCustodianThirdPartySecret) VALUES ('550e8400-e29b-41d4-a716-4466554413a0', 'third_party', 'Third Party (GreenButtonData)', 'http://services.greenbuttondata.org/ThirdParty/RetailCustomer/ScopeSelection', 'http://services.greenbuttondata.org/ThirdParty/espi/1_1/Notification', 'http://services.greenbuttondata.org/ThirdParty/espi/1_1/OAuthCallBack', 'secret')
INSERT INTO application_information_scopes (application_information_id, scope) VALUES (1, 'FB=4_5_15;IntervalDuration=3600;BlockDuration=monthly;HistoryLength=13');
INSERT INTO application_information_scopes (application_information_id, scope) VALUES (1, 'FB=4_5_16;IntervalDuration=3600;BlockDuration=monthly;HistoryLength=13');