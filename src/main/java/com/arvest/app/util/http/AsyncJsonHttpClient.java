package com.arvest.app.util.http;

import org.asynchttpclient.*;

/**
 * Created by vinodarichella on 6/17/17.
 */
public class AsyncJsonHttpClient implements AsyncHttpClient {

    private final org.asynchttpclient.AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();

    @Override
    public ListenableFuture<Response> get(String url) {
        return asyncHttpClient.prepareGet(CleanUrl.startWithHttp(url)).execute();
    }

    @Override
    public ListenableFuture<Response> post(String url, String json) {
        RequestBuilder builder = new RequestBuilder("POST");
        Request request = builder.setUrl(CleanUrl.startWithHttp(url)).setBody(json).setHeader("Content-Type", "application/json").build();
        return asyncHttpClient.executeRequest(request);
    }

    @Override
    public ListenableFuture<Response> put(String url, String json) {
        RequestBuilder builder = new RequestBuilder("PUT");
        Request request = builder.setUrl(CleanUrl.startWithHttp(url)).setBody(json).setHeader("Content-Type", "application/json").build();
        return asyncHttpClient.executeRequest(request);
    }

    @Override
    public ListenableFuture<Response> delete(String url) {
        return asyncHttpClient.prepareDelete(CleanUrl.startWithHttp(url)).execute();
    }
}
