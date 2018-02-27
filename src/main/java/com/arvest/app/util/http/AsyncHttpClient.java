package com.arvest.app.util.http;

import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;

/**
 * Created by vinodarichella on 6/17/17.
 */
public interface AsyncHttpClient {

    ListenableFuture<Response> get(String url);

    ListenableFuture<Response> post(String url, String json);

    ListenableFuture<Response> put(String url, String json);

    ListenableFuture<Response> delete(String url);
}
