package com.arvest.app.util.http;

/**
 * Created by vinodarichella on 6/17/17.
 */
public class CleanUrl {

    public static String startWithHttp(String url) {
        if(!url.startsWith("http")) {
            url = "http://" + url;
        }
        return url;
    }
}
