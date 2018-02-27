package com.arvest.app.service;

import com.arvest.app.config.RobinhoodUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UrlService {

    @Autowired
    RobinhoodUrls robinhoodUrls;

    @PostConstruct
    public void printUrls() {
        System.out.println(robinhoodUrls.getAccounts());
        System.out.println(robinhoodUrls.getQuotes());
        System.out.println(robinhoodUrls.getAchDepositSchedules());
    }
}
