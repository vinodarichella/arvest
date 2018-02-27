package com.arvest.app.service;

import com.arvest.ArvestApplicationTests;
import com.arvest.app.config.ClientConfig;
import com.arvest.app.domain.Quote;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import static org.junit.Assert.*;

public class QuotesServiceTest extends ArvestApplicationTests{

    @Autowired
    QuotesService quotesService;

    @Autowired
    ClientConfig clientConfig;

    @Value("${client.ssl.trust-store}")
    private Resource trustStore;

    @Value("${client.ssl.trust-store-password}")
    private String trustStorePassword;

    @Test
    public void testValues() {
        try {
            System.out.println(trustStorePassword);
            if (trustStore.getFile() != null) {
                System.out.println("File found");
            }
            if (clientConfig != null) {
                System.out.println("client config not null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getQuote() {
        try {
            Quote quote = quotesService.getQuote("https://api.robinhood.com/quotes/VOO/");
            System.out.println(quote);
            Assert.assertNotNull(quote);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}