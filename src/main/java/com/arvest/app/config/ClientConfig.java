package com.arvest.app.config;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ClientConfig {

    @Value("${client.ssl.trust-store}")
    private Resource trustStore;

    @Value("${client.ssl.trust-store-password}")
    private String trustStorePassword;

    public CloseableHttpClient httpClient() throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setSSLSocketFactory(sslConnectionSocketFactory())
                .build();
        return httpClient;
    }

    public SSLConnectionSocketFactory sslConnectionSocketFactory() throws Exception {
        return new SSLConnectionSocketFactory(sslContext(), NoopHostnameVerifier.INSTANCE);
    }

    public SSLContext sslContext() throws Exception {
        return SSLContextBuilder.create()
                .loadTrustMaterial(trustStore.getFile(), trustStorePassword.toCharArray()).build();
    }
}
