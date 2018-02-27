package com.arvest.app.util.http;

/**
 * Created by vinodarichella on 6/17/17.
 */
public class UrlEncodedHttpClient extends HttpClientBase {
    @Override
    protected String getMediaType() {
        return "application/x-www-form-urlencoded";
    }
}
