package com.arvest.app.service;

import com.arvest.app.config.ClientConfig;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;

@Service
public class HttpService {

    @Autowired
    ClientConfig clientConfig;

    public String httpPost(HttpPost post) {
        return requestData(post);
    }

    private String requestData(HttpRequestBase request) {
        try (CloseableHttpClient httpClient = clientConfig.httpClient()) {
            httpClient.execute(request);
            CloseableHttpResponse httpResponse = httpClient.execute(request);
            return CharStreams.toString(new InputStreamReader(httpResponse.getEntity().getContent()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String httpGet(HttpGet get) {
        return requestData(get);
    }

}
