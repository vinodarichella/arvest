package com.arvest.app.util.http;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public interface HttpClient {
    String get(String url);

    String post(String url);

    String post(String json, HttpEntityEnclosingRequestBase request);

    String put(String url);

    String delete(String url);
}
