package com.arvest.app.util.http;

import com.google.common.io.CharStreams;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.InputStreamReader;

@Service
public class HttpClientBase implements HttpClient {

    @Value("${client.ssl.trust-store}")
    private Resource trustStore;

    @Value("${client.ssl.trust-store-password}")
    private String trustStorePassword;

    @Override
    public String get(String url) {
        return requestdata(new HttpGet(url));
    }

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

    @Override
    public String post(String url) {
        return null;
    }

    public String post(HttpRequestBase requestBase) {
        return requestdata(requestBase);
    }

    @Override
    public String post(String json, HttpEntityEnclosingRequestBase request) {
        return requestdata(request, json);
    }

    @Override
    public String put(String url) {
        return null;
    }

    @Override
    public String delete(String url) {
        return null;
    }

    protected String getMediaType() {
        return "application/json";
    }

    protected String requestdata(HttpRequestBase request) {
        try(CloseableHttpClient httpClient = httpClient()) {
            final CloseableHttpResponse response = httpClient.execute(request);
            return CharStreams.toString(new InputStreamReader(response.getEntity().getContent()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected String requestdata(HttpEntityEnclosingRequestBase request, String json) {
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            StringEntity entity = new StringEntity(json);
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, getMediaType()));
            request.setEntity(entity);
            final CloseableHttpResponse response = httpClient.execute(request);
            return CharStreams.toString(new InputStreamReader(response.getEntity().getContent()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}