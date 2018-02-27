package com.arvest.app.service;

import com.arvest.app.config.ClientConfig;
import com.arvest.app.config.RobinhoodUrls;
import com.arvest.app.domain.Token;
import com.arvest.app.util.GsonUtil;
import com.arvest.app.util.http.UrlEncodedHttpClient;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    RobinhoodUrls urls;

    @Autowired
    HttpService httpService;

    public Token authenticate() throws Exception {
        HttpPost httpPost = new HttpPost(urls.getAuth());
        List<NameValuePair> params = Lists.newArrayList();

        String username = System.getProperty("username");
        String password = System.getProperty("password");

        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        String response = httpService.httpPost(httpPost);
        return GsonUtil.fromJson(response, Token.class);
    }
}
