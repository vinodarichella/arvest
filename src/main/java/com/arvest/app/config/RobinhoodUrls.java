package com.arvest.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "robinhood")
@Data
public class RobinhoodUrls {

    private String mfa;

    private String marginInterestCharges;

    private String marginUpgrades;

    private String instruments;

    private String quotes;

    private String accounts;

    private String orders;

    private String subscriptionFees;

    private String idDocuments;

    private String portfolios;

    private String markets;

    private String wireRelationships;

    private String achQueuedDeposit;

    private String subscriptions;

    private String wireTransfers;

    private String dividends;

    private String notificationSettings;

    private String applications;

    private String user;

    private String achRelationships;

    private String achDepositSchedules;

    private String achIavAuth;

    private String notifications;

    private String achTransfers;

    private String positions;

    private String watchlists;

    private String documentRequests;

    private String edocuments;

    private String passwordReset;

    private String passwordChange;

    private String auth;

}
