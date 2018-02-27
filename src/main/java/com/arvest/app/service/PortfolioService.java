package com.arvest.app.service;

import com.arvest.app.config.ClientConfig;
import com.arvest.app.config.RobinhoodUrls;
import com.arvest.app.domain.Portfolio;
import com.arvest.app.domain.PortfolioResults;
import com.arvest.app.domain.Positions;
import com.arvest.app.domain.Token;
import com.arvest.app.util.GsonUtil;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    RobinhoodUrls urls;

    @Autowired
    HttpService httpService;

    public Portfolio getPortfolio(Token token) {
        HttpGet get = new HttpGet(urls.getPortfolios());
        get.addHeader(HttpHeaders.AUTHORIZATION, "Token " + token.getToken());
        String response = httpService.httpGet(get);
        return GsonUtil.fromJson(response, PortfolioResults.class).getResults()[0];
    }
}
